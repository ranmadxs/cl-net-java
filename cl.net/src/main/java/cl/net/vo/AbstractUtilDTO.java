package cl.net.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <p>
 * </p>
 * <p>
 * $Id: AbstractUtilDTO.java,v 1.1 2009-09-10 21:47:57 pcollaog Exp $
 * </p>
 * 
 * @author pcollaog
 * @version $Revision: 1.1 $
 */
public abstract class AbstractUtilDTO implements Serializable {

	private static final long serialVersionUID = -8868825424011655896L;

	@Override
	public String toString() {
		return beanToString(this);
	}

	protected static String beanToString(Object obj) {
		return ToStringBuilder.reflectionToString(obj,ToStringStyle.MULTI_LINE_STYLE);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);		
	}
	
	
	// **** Formateadores y conversores
	// ******************************** 
	/**
	 * 
	 * @param value
	 * @param pformat
	 * @return
	 */
	public String formatS1000Decimal(BigDecimal value , String pformat){
		String ret = "";
		DecimalFormat nf = new DecimalFormat(pformat);
		ret = nf.format(value);
		
		String[] split1 = ret.split(","); 
		
		
		NumberFormat nfm = NumberFormat.getInstance();
		
		String mil = nfm.format( new BigDecimal(split1[0]));
		
		return mil+","+split1[1];
	}

	/**
	 * 
	 * @param value
	 * @param pformat
	 * @return
	 */
	public BigDecimal formatS1000Decimal(String value , String pformat){
		DecimalFormat nf = new DecimalFormat(pformat);
		Object obj = "";
		try {
			obj = nf.parse(value);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new BigDecimal( obj.toString() );
	}

	/***
     * Parsea a un numerico llevando a BigDecimal
     * @return
     */
	public BigDecimal objectToBigDecimal(Object obj){
	  BigDecimal  ret = new BigDecimal(0);
	  
	  try {
		  
	  
	  if(obj == null) 
		  
		  return ret;
	  
	  else{
		  
		  BigDecimal bd = new BigDecimal(obj.toString().trim());
		  
		  ret = bd;
	  }
	  }catch(Exception e){
		  return new BigDecimal(0);
	  } 
	  	
	  return ret;
	}
	

	/***
     * Parsea a un String aplica trim upper y si es null aplica vacio
     * @return
     */
	public String objectToString(Object obj){
	  String  ret = new String("");
	  
	  try {
		  
	  
	  if(obj == null) 
		  
		  return "";
	  
	  else{
		  
		  String bd = new String(obj.toString().trim().toUpperCase());
		  
		  ret = bd;
	  }
	  }catch(Exception e){
		  return new String("");
	  } 
	  	
	  return ret;
	}
	
	
    /***
     * Parsea aun numerico llevando a Long
     * @return
     */
	public long objectToLong(Object obj){
	  long  ret = 0;
	  
	  try {
		  
	  
	  if(obj == null) 
		  
		  return Long.parseLong("0");
	  
	  else{
		  
		  BigDecimal bd = new BigDecimal((""+obj.toString()).trim());
		  
		  ret = bd.longValue();
	  }
	  }catch(Exception e){
		  return Long.parseLong("0");
	  } 
	  	
	  return ret;
	}	
}
