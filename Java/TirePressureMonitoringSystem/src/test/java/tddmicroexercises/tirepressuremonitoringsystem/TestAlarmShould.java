package tddmicroexercises.tirepressuremonitoringsystem;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TestAlarmShould {

    @Mock
    private Sensor sensor;

    @Test
    public void return_alarm_not_on_if_check_has_not_been_done() {
        Alarm alarm = new Alarm(sensor);
        assertEquals(false, alarm.isAlarmOn());
    }

    @Test
    public void return_alarm_on_when_psi_pressure_between_low_and_high() {
        given(sensor.popNextPressurePsiValue()).willReturn(Double.valueOf(15));

        Alarm alarm = new Alarm(sensor);
        alarm.check();

        assertEquals(true, alarm.isAlarmOn());
    }
}
