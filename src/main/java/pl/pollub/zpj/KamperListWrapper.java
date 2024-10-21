package pl.pollub.zpj;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Kampers")
public class KamperListWrapper {
    private List<Kamper> kampers = new ArrayList<>();
    @XmlElement(name = "Kamper")
    public List<Kamper> getKampers() {
        return kampers;
    }

    public void setKampers(List<Kamper> kampers) {
        this.kampers = kampers;
    }
}
