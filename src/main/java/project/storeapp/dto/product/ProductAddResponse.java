package project.storeapp.dto.product;

import java.util.List;

public class ProductAddResponse {
    private String successMessage;
    private String skippedMessage;
    private List<ProductRequestDTO> skippedProducts;

    public ProductAddResponse(String successMessage, String skippedMessage, List<ProductRequestDTO> skippedProducts) {
        this.successMessage = successMessage;
        this.skippedMessage = skippedMessage;
        this.skippedProducts = skippedProducts;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public String getSkippedMessage() {
        return skippedMessage;
    }

    public List<ProductRequestDTO> getSkippedProducts() {
        return skippedProducts;
    }
}
