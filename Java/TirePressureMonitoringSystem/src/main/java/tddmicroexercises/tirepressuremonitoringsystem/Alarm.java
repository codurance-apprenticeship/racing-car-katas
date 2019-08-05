package tddmicroexercises.tirepressuremonitoringsystem;

public class Alarm {
    private final double LowPressureThreshold = 17;
    private final double HighPressureThreshold = 21;

    Sensor sensor;

    boolean alarmOn = false;

    public Alarm(Sensor sensor) {
        this.sensor = sensor;
    }

    public void check() {
        double psiPressureValue = sensor.popNextPressurePsiValue();

        if (isBelowThreshold(psiPressureValue) || isAboveThreshold(psiPressureValue)) {
            alarmOn = true;
        }
    }

    private boolean isBelowThreshold(double psiPressureValue) {
        return psiPressureValue < LowPressureThreshold;
    }

    private boolean isAboveThreshold(double psiPressureValue) {
        return psiPressureValue > HighPressureThreshold;
    }

    public boolean isAlarmOn() {
        return alarmOn;
    }
}
