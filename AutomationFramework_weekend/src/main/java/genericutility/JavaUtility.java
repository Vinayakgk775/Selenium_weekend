package genericutility;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

public class JavaUtility {
	/**
	 * This method is used to capture the system date and time
	 * @return
	 */
	
	public String getSystemTime() {
		return LocalDateTime.now().toString().replace(":", "-");
	}

}
