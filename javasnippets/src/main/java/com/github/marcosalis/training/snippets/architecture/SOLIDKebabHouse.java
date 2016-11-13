package com.github.marcosalis.training.snippets.architecture;

import java.util.List;

/**
 * Examples of violation of the S.O.L.I.D. principles when modelling a Kebab House's behaviour.
 * <p>
 * Do NOT do this!!
 */
@SuppressWarnings("WeakerAccess, unused")
public abstract class SolidKebabHouse {

    // 1- Single Responsibility Principle violation

    public abstract boolean purchaseKebab(KebabHouseClient client, Kebab kebab);

    public abstract List<String> getKebabAllergens();

    public abstract boolean openKebabHouse();

    public abstract boolean closeKebabHouse();

    public static abstract class KebabHouseClient {
    }

    public enum Meat {
        LAMB,
        CHICKEN,
        MIXED;

        public float getMeatPrice() {
            switch (this) {
                case CHICKEN:
                    return 1.75f;
                case LAMB:
                case MIXED:
                default:
                    return 2f;
            }
        }
    }

    public static abstract class Kebab {

        private Meat meat;

        public void addMeat(Meat meat) {
            this.meat = meat;
        }

        // 2- Open/Closed Principle violation
        public final float getPrice() {
            if (this instanceof WrapKebab || this instanceof SandwichKebab) {
                return meat.getMeatPrice() + 2f;
            } else {
                return ((VegetarianKebab) this).getVegetablesPrice() + 1.5f;
            }
        }
    }

    public static class WrapKebab extends Kebab {
    }

    public static class SandwichKebab extends Kebab {
    }

    public static abstract class VegetarianKebab extends Kebab {
        @Override
        public void addMeat(Meat meat) {
            // 3- Liskov Substitution Principle violation
            throw new IllegalStateException("Can't add meat to a vegetarian kebab");
        }

        public abstract float getVegetablesPrice();
    }

    // 4- Interface Segregation principle violation

    public interface OnKebabHouseActionListener {
        void onKebabPurchased(Kebab kebab);

        void onKebabReady(Kebab kebab);

        void onLastOrders();

        void onMeatFinished(Meat meat);
    }

    // 5- Dependency Inversion principle violation

    public static class CardPaymentProvider {
    }

    public final CardPaymentProvider paymentProvider = new CardPaymentProvider();

    public SolidKebabHouse() {
    }

}