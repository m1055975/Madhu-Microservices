package com.mindtree.consumer.listener;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.consumer.dto.MessageDto;

@Service
public class KafkaConsumer {
	
	    
	    private JavaMailSenderImpl mailSenderImpl=new JavaMailSenderImpl();
	    
	    
	    @Bean
	    public JavaMailSender getJavaMailSender()
	    {
	    	mailSenderImpl.setHost("smtp.gmail.com");
	    	mailSenderImpl.setPort(587);
	    	
	    	Properties properties=mailSenderImpl.getJavaMailProperties();
	    	properties.put("mail.transport.protocol", "smtp");
	    	properties.put("mail.smtp.auth", "true");
	    	properties.put("mail.smtp.starttls.enable", "true");
	    	properties.put("mail.debug", "true");
	    	properties.put("mail.smtp.connectiontimeout", "5000");
	        properties.put("mail.smtp.timeout", "50000");
	    	
	    	return mailSenderImpl;
	    	
	    }
	    

	    @KafkaListener(topics = "order",groupId = "group_id")
        public void readMessage(String byteData) throws JsonMappingException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

            MessageDto messageDto = objectMapper.readValue(byteData, MessageDto.class);
            
            System.out.println(messageDto);
	    	
	        SimpleMailMessage mail= new SimpleMailMessage();
			
			mailSenderImpl.setUsername("anjalireddyar2@gmail.com");
			
			mailSenderImpl.setPassword("AnjaliAR@123#");
			
			mail.setTo("jeebanjyoti.oec@gmail.com");
			mail.setSubject("Your Order Details");
			mail.setText("**** User Id :- "+messageDto.getUserId()+ "   Order Id :- " 
			+messageDto.getOrderId()+ "User Name : - "
					+messageDto.getUserName() + "   Restaurant Name :- "
			+ messageDto.getRestaurantName()+ "   Total Amount :- " 
					+messageDto.getTotalAmount()+ " ****  "
			+ messageDto.getMessage()+  " ******* " );
			mailSenderImpl.send(mail);
	       
	    }
	}



