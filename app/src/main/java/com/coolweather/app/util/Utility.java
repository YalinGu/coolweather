package com.coolweather.app.util;

import android.text.TextUtils;
import android.util.Log;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.Country;
import com.coolweather.app.model.Province;

/**
 * Created by GuYL on 2016/4/15.
 */
public class Utility {

    /**
     * 解析和处理服务器返回的省级数据
     */
    public synchronized static boolean handleProvincesResponse(
            CoolWeatherDB coolWeatherDB, String response) {
        if(!TextUtils.isEmpty(response)){
            Log.d("ChooseAreaActivity","333333333");
            String[] allProvinces = response.split(",");
            if(allProvinces!= null && allProvinces.length>0){
                Log.d("ChooseAreaActivity","444444444444");
                for (String p : allProvinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //将解析出来的数据存储到Province表
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCitiesResponse(
            CoolWeatherDB coolWeatherDB, String response, int provinceId) {
        if(!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if(allCities!= null && allCities.length>0){
                for (String p : allCities){
                    String[] array = p.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    //将解析出来的数据存储到City表
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountriesResponse(
            CoolWeatherDB coolWeatherDB, String response, int cityId) {
        if(!TextUtils.isEmpty(response)){
            String[] allCountries = response.split(",");
            if(allCountries!= null && allCountries.length>0){
                for (String p : allCountries){
                    String[] array = p.split("\\|");
                    Country country = new Country();
                    country.setCountryCode(array[0]);
                    country.setCountryName(array[1]);
                    country.setCityId(cityId);
                    //将解析出来的数据存储到City表
                    coolWeatherDB.saveCountry(country);
                }
                return true;
            }
        }
        return false;
    }


}
