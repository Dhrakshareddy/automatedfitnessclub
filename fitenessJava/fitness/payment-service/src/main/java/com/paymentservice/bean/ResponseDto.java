package com.paymentservice.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ResponseDto {

	private UserBean userBean;

	private PaymentBean paymentBean;

}
