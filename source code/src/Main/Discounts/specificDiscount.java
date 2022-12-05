package Main.Discounts;

import Main.Services.Service;

public class specificDiscount extends DiscountDecorator{

    public specificDiscount(Service service)
    {
        super(service);
    }

    @Override
    public double calculateCost() {
        return (service.getCost() - 0.2*service.getCost());
    }
}
