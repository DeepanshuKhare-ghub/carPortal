package in.springsecurity.payload.carDto;

public class CarDto {

    private Long modelId;
    private Long yearId;
    private Long brandId;
    private Long transmissionId;
    private Long fuelId;
    private Long statusId;

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getYearId() {
        return yearId;
    }

    public void setYearId(Long yearId) {
        this.yearId = yearId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getTransmissionId() {
        return transmissionId;
    }

    public void setTransmissionId(Long transmissionId) {
        this.transmissionId = transmissionId;
    }

    public Long getFuelId() {
        return fuelId;
    }

    public void setFuelId(Long fuelId) {
        this.fuelId = fuelId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
