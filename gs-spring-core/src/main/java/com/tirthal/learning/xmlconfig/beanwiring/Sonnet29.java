package com.tirthal.learning.xmlconfig.beanwiring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sonnet29 implements Poem {

	private static final Logger log = LoggerFactory.getLogger(Sonnet29.class);
	
	private static String[] LINES = {
			"When,indisgracewithfortuneandmen'seyes,",
			"I allalonebeweepmyoutcaststate",
			"Andtroubledeafheavenwithmybootlesscries",
			"Andlookuponmyselfandcursemyfate,",
			"Wishingmeliketoonemorerichinhope,",
			"Featuredlikehim,likehimwithfriendspossess'd,",
			"Desiringthisman'sartandthatman'sscope,",
			"WithwhatImostenjoycontentedleast;",
			"Yetinthesethoughtsmyselfalmostdespising,",
			"HaplyIthinkonthee,andthenmystate,",
			"Liketothelarkatbreakofdayarising",
			"Fromsullenearth,singshymnsatheaven'sgate;",
			"Forthysweetloveremember'dsuchwealthbrings",
			"ThatthenIscorntochangemystatewithkings."};
	
	public Sonnet29() {
	}
	
	public void recite() {		
		for(int i=0; i < LINES.length; i++)
		{
			log.debug(LINES[i]);
		}
	}

}
