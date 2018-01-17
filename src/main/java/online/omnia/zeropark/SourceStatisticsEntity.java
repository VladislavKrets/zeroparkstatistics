package online.omnia.zeropark;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by lollipop on 13.10.2017.
 */
@Entity
@Table(name = "source_statistics")
public class SourceStatisticsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "campaign_id")
    private String campaignId;
    @Column(name = "spent")
    private double spent;
    @Column(name = "conversions")
    private int conversions;
    @Column(name = "receiver")
    private String receiver;
    @Column(name = "account_id")
    private int account_id;
    @Column(name = "date")
    private Date date;
    @Column(name = "campaign_name")
    private String campaignName;
    @Column(name = "time")
    private Time time;

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public double getSpent() {
        return spent;
    }

    public void setSpent(double spent) {
        this.spent = spent;
    }

    public int getConversions() {
        return conversions;
    }

    public void setConversions(int conversions) {
        this.conversions = conversions;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SourceStatisticsEntity{" +
                "id=" + id +
                ", campaignId='" + campaignId + '\'' +
                ", spent=" + spent +
                ", conversions=" + conversions +
                ", receiver='" + receiver + '\'' +
                ", account_id=" + account_id +
                ", date=" + date +
                ", campaignName='" + campaignName + '\'' +
                ", time=" + time +
                '}';
    }
}
