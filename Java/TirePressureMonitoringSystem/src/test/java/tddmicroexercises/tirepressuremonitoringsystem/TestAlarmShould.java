package tddmicroexercises.tirepressuremonitoringsystem;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TestAlarmShould {

    @Mock
    private Sensor sensor;

    private Alarm alarm;

    @Before
    public void setUp() {
        alarm = new Alarm(sensor);
    }

    @Test
    public void return_alarm_on_is_false_if_check_has_not_been_done() {
        assertFalse(alarm.isAlarmOn());
    }

    @Test
    public void return_alarm_on_is_true_when_psi_pressure_lower_than_low_threshold() {
        given(sensor.popNextPressurePsiValue()).willReturn(Double.valueOf(16));

        alarm.check();

        assertTrue(alarm.isAlarmOn());
    }

    @Test
    public void return_alarm_on_is_false_when_psi_pressure_between_low_and_high_threshold() {
        given(sensor.popNextPressurePsiValue()).willReturn(Double.valueOf(17));

        alarm.check();

        assertFalse(alarm.isAlarmOn());
    }

    @Test
    public void return_alarm_on_is_true_when_psi_pressure_higher_than_high_threshold() {
        given(sensor.popNextPressurePsiValue()).willReturn(Double.valueOf(22));

        alarm.check();

        assertTrue(alarm.isAlarmOn());
    }

    @Test
    public void use_sensor_class() {
        given(sensor.popNextPressurePsiValue()).willReturn(Double.valueOf(30));

        alarm.check();

        verify(sensor, times(1)).popNextPressurePsiValue();
        assertTrue(alarm.isAlarmOn());
    }
}
