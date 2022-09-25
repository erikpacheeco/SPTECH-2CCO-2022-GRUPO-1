package petfinder.petfinderapi.requisicao;

import java.util.List;

import javax.validation.constraints.NotNull;

public class DtoPatchDemanda {
    
    // attributes
    @NotNull
    private Integer userId;
    @NotNull
    private String action;

    // constructors
    public DtoPatchDemanda() {}

    // methods
    public List<String> possibleActions() {
        return List.of("accept", "decline", "accept/decline", null);
    }

    // getters and setters
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
}
