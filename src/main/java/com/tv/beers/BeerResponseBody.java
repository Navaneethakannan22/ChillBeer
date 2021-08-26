package com.tv.beers;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BeerResponseBody {
    BeerResponseBody() {

    }

    private int id;
    private String name;
    private String tagline;
    private String first_brewed;
    private String description;
    private String image_url;
    private double abv;
    private int ibu;
    private int target_fg;
    private int target_og;
    private int ebc;
    private int srm;
    private double ph;
    private int attenuation_level;
    private Volume volume;
    private Boil_volume boil_volume;
    private Method method;
    private Ingredients ingredients;
    private List<String> food_pairing;
    private String brewers_tips;
    private String contributed_by;
    private int statusCode;

//==================================

    @Getter
    @Setter
    public static class Volume {
        private int value;
        private String unit;

    }

    //==================================
    @Getter
    @Setter
    public static class Boil_volume {
        private int value;
        private String unit;
    }

    //==================================
    @Getter
    @Setter
    public static class Temp {
        private int value;
        private String unit;
    }

    //==================================
    @Getter
    @Setter
    public static class Mash_temp {
        private Temp temp;
        private int duration;
    }

    //==================================
    @Getter
    @Setter
    public static class Fermentation {
        private Temp temp;
    }

    //==================================
    @Getter
    @Setter
    public static class Method {
        private List<Mash_temp> mash_temp;
        private Fermentation fermentation;
        private String twist;
    }

    //==================================
    @Getter
    @Setter
    public static class Amount {
        private double value;
        private String unit;
    }

    //==================================
    @Getter
    @Setter
    public static class Malt {
        private String name;
        private Amount amount;
    }

    //==================================
    @Getter
    @Setter
    public static class Hops {
        private String name;
        private Amount amount;
        private String add;
        private String attribute;
    }

    //==================================
    @Getter
    @Setter
    public static class Ingredients {
        private List<Malt> malt;
        private List<Hops> hops;
        private String yeast;
    }

//==================================

}
