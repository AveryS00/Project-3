import org.junit.Test;
import java.util.regex.Pattern;
public class test {
	@Test
	public void testThis(){
	String newString = "\t\t\t\tfuck me";
	while(newString.contains("\t")){
		//System.out.print(newString);
		newString = newString.replaceFirst("\t","");
	}
	System.out.print(newString);
	}
	

		@Test
	    public void main() {
	    
	        String text = "A sep Text sep With sep Many sep Separators";
	        
	        String patternString = "sep";
	        Pattern pattern = Pattern.compile(patternString);
	        
	        String[] split = pattern.split(text);
	        
	        System.out.println("split.length = " + split.length);
	        
	        for(String element : split){
	            System.out.println("element = " + element);
	        }
	    }
	}

