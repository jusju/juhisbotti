package JuhisBotti.botti;

import java.util.Date;

public class SysteemiKelloLahde implements KelloLahde {
	
	public Date haeAjanhetki() {
		return new Date(System.currentTimeMillis());
	}

}
