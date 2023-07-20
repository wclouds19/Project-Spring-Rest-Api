package bootcamp.rest.dto;

import jakarta.validation.constraints.NotEmpty;

public class CategoryDataTransferObject {
    
    @NotEmpty(message="Name is required")
    private String name;

    @NotEmpty(message="Description is required")
    private String description;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
}
