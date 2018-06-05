package test.popda.retrifittest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class WeatherDay {
    private class WeatherTemp {
        Double temp;
        Double temp_min;
        Double temp_max;
    }

    public class WeatherDescription {
        String icon;
    }

    @SerializedName("main")
    private WeatherTemp temp;

    @SerializedName("weather")
    private List<WeatherDescription> desctiption;

    @SerializedName("name")
    private String city;


    @SerializedName("dt_txt")
    private String dtTxt;

    public WeatherDay(WeatherTemp temp, List<WeatherDescription> desctiption) {
        this.temp = temp;
        this.desctiption = desctiption;
    }


    public String getTemp() { return String.valueOf(temp.temp); }

    public String getTempMin() { return String.valueOf(temp.temp_min); }

    public String getTempMax() { return String.valueOf(temp.temp_max); }

    public String getTempInteger() { return String.valueOf(temp.temp.intValue()); }

    public String getTempWithDegree() { return String.valueOf(temp.temp.intValue()-273) + "\u00B0"; }

    public String getDtTxt() { return dtTxt; }

    public String getCity() { return city; }

    public String getIcon() { return desctiption.get(0).icon; }

    public String getIconUrl() {
        String iconUrl = new StringBuilder().append("http://openweathermap.org/img/w/").append(desctiption.get(0).icon).append(".png").toString();
        return iconUrl;
    }
}
