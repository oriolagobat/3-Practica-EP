import data.DocPath;
import data.Nif;
import java.util.Date;

public class Citizen {
    Nif nif;
    Date valdate;
    DocPath savePath;

    public Citizen() {
        this.nif = null;
        this.valdate = null;
        this.savePath = null;
    }

    public void setCitizenNif(Nif nif) {
        this.nif = nif;
    }

    public void setCitizenValDate(Date valdate) {
        this.valdate = valdate;
    }

    public void setCitizenSavePath(DocPath savePath) {
        this.savePath = savePath;
    }

    public Nif getCitizenNif() {
        return this.nif;
    }

    public Date getCitizenValDate() {
        return this.valdate;
    }

    public DocPath getCitizenSavePath() {
        return this.savePath;
    }
}
