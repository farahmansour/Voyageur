package application;


	
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="voyageur")
public class VoyageurListWrapper 
{
	private List<Voyageur> voyageur;	
    @XmlElement(name= "voyageur")
	public List<Voyageur> getVoyageur()
	{
    	return voyageur;
		
	}
			public void setVoyageur(List<Voyageur> voyageur)
		{
			this.voyageur=voyageur;
		}
		
	}


