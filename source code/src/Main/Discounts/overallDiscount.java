package Main.Discounts;

import Main.Services.Service;

public class overallDiscount extends DiscountDecorator{

    public overallDiscount(Service service)
    {
        super(service);
        this.setCost(service.getCost());
        this.setClient(service.getClient());
    }

    @Override
    public double calculateCost() {
        return (service.getCost() - 0.1*service.getCost());
    }
}
