package test.popda.retrifittest.api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import test.popda.retrifittest.model.WeatherDay;
import test.popda.retrifittest.model.WeatherForecast;


public class WeatherAPI {
    public static String KEY = "9b50aa81f5cd02827e215de871c6129d";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static Retrofit retrofit = null;

    public interface ApiInterface {
        @GET("weather")
        Call<WeatherDay> getToday(@Query(value = "q", encoded = true) String cityName,
                                  @Query("appid") String appid);

        @GET("forecast")
        Call<WeatherForecast> getForecast(@Query(value = "q", encoded = true) String cityName,
                                          @Query("appid") String appid);
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
