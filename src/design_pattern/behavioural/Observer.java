package design_pattern.behavioural;

import java.util.ArrayList;
import java.util.List;

interface Device{
    void showTemp(float temp);
}
class Mobile implements Device{
    @Override
    public void showTemp(float temp) {
        System.out.println("current temp in mobile is "+temp);
    }
}
class Laptop implements Device{
    @Override
    public void showTemp(float temp) {
        System.out.println("current temp in Laptop is "+temp);
    }
}
class WeatherStation{
    private List<Device> devices = new ArrayList<>();
    public void addDevice(Device device){
        this.devices.add(device);
    }
    public void removeDevice(Device device){
        devices.remove(device);
    }
    public void setTemp(float temp){
        devices.forEach(device -> device.showTemp(temp));
    }
}

public class Observer {
    public static void main(String[] args) {
        Device mobile = new Mobile();
        Device laptop = new Laptop();
        WeatherStation weatherStation = new WeatherStation();
        weatherStation.addDevice(mobile);
        weatherStation.addDevice(laptop);
        weatherStation.setTemp(35.6f);
        weatherStation.removeDevice(laptop);
        weatherStation.setTemp(36.2f);
    }
}
