package org.adenaden.tutorial.jvm.workshop.dto;

import lombok.Builder;

@Builder
public class DokuHostedRequestDTO {
	
	private String MALLID;
	private String CHAINMERCHANT;
	private String AMOUNT;
	private String PURCHASEAMOUNT;
	private String TRANSIDMERCHANT;
	private String PAYMENTTYPE;
	private String WORDS;
	private String REQUESTDATETIME;
	private String CURRENCY;
	private String PURCHASECURRENCY;
	private String SESSIONID;
	private String NAME;
	private String EMAIL;
	private String BASKET;
	
}
