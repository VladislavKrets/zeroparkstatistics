package online.omnia.zeropark;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lollipop on 11.10.2017.
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.update();
    }

    private void update() {
        List<AccountsEntity> accountsEntities = MySQLDaoImpl.getInstance().getAccountsEntities("Zeropark");

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(List.class, new JsonCampaignListDeserializer());
        Gson gson = builder.create();
        List<CampaignJson> campaignJsons = new ArrayList<>();
        String answer;
        for (AccountsEntity accountsEntity : accountsEntities) {
            if (accountsEntity.getApiKey() == null) continue;
            answer = HttpMethodUtils
                    .getMethod("https://panel.zeropark.com" + "/api/stats/campaign/all?interval=YESTERDAY",
                            accountsEntity.getApiKey());

            campaignJsons = gson.fromJson(answer, List.class);
            SourceStatisticsEntity sourceStatisticsEntity;
            for (CampaignJson campaignJson : campaignJsons) {
                sourceStatisticsEntity = new SourceStatisticsEntity();
                sourceStatisticsEntity.setAccount_id(accountsEntity.getAccountId());
                sourceStatisticsEntity.setCampaignId(campaignJson.getCampaignId());
                sourceStatisticsEntity.setConversions(campaignJson.getConversions());
                sourceStatisticsEntity.setSpent(campaignJson.getSpent());
                sourceStatisticsEntity.setDate(new Date(System.currentTimeMillis() - 86400000L));
                sourceStatisticsEntity.setReceiver("API");
                System.out.println(sourceStatisticsEntity);
                MySQLDaoImpl.getInstance().addSourceStatistics(sourceStatisticsEntity);
            }
        }
        MySQLDaoImpl.getSessionFactory().close();
    }
}
