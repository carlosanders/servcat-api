package mb.dabm.servcatapi.projection;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public interface IdentificationDTO {
    Long getCodGen();
    String getFsc();
    String getNiin();
    String getNsn();
    String getItemName();
    String getInc();
    String getTiic();
    String getRpdmrc();
    String getFmsn();

}
