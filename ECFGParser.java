package com.cummins.cs.hsdi.poc;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cummins.cs.hsdi.poc.properties.PropertiesLoader;
import com.cummins.ctp.util.UdsToScale;

public class ECFGParser {
	
	public static void main(String[] args) throws Exception {
		String message="{\"Message_Format_Version\":\"1.0\",\"Source_Id_Type\":\"Engine_Serial_Number\",\"Source_Id\":\"12345678\",\"Data_Sampling_Config_Id\":5000,\"Number_of_Samples\":1,\"Samples\":[{\"RawEquipmentParameters\":{\"1\":\"00FF\",\"2\":\"06\",\"3\":\"01\",\"4\":\"1F000000\",\"5\":\"16\",\"6\":\"0700006E\",\"7\":\"FE440001\",\"8\":\"5FDA1FFF\",\"9\":\"04F57206\",\"10\":\"4A\",\"11\":\"0C\",\"12\":\"00\",\"13\":\"00\",\"14\":\"58\",\"15\":\"7D\",\"16\":\"0000\",\"17\":\"B3\",\"18\":\"34BC\",\"19\":\"AD44\",\"20\":\"9043\",\"21\":\"4339\",\"22\":\"403A\",\"23\":\"9044\",\"24\":\"B33B\",\"25\":\"A3\",\"26\":\"4432\",\"27\":\"556A\",\"28\":\"D041\",\"29\":\"D3\",\"30\":\"A3C2\",\"31\":\"C443\",\"32\":\"1102\",\"33\":\"13\",\"34\":\"523\",\"35\":\"E46F\",\"36\":\"2CF4\",\"37\":\"9043\",\"38\":\"3321\",\"39\":\"9933\",\"40\":\"8746\",\"41\":\"A224\",\"42\":\"F0\",\"43\":\"B473\",\"44\":\"B3\",\"45\":\"A2\",\"46\":\"2345\",\"47\":\"431A\",\"48\":\"B349\",\"49\":\"90\",\"50\":\"45\",\"51\":\"23\",\"52\":\"2394\",\"53\":\"ABCD\",\"54\":\"DCBA\",\"55\":\"94\",\"56\":\"47\",\"57\":\"AA\",\"58\":\"BB\",\"59\":\"CC\",\"60\":\"0\",\"61\":\"DDDD\",\"62\":\"439E\",\"63\":\"432E\",\"64\":\"31\",\"65\":\"DDCD\",\"66\":\"CCDC\",\"67\":\"2582\",\"68\":\"266E\",\"69\":\"250A\",\"70\":\"26B5\",\"71\":\"2691\",\"72\":\"2582\",\"73\":\"266E\",\"74\":\"264A\",\"75\":\"2750\",\"76\":\"2700\",\"77\":\"26B5\",\"78\":\"26B5\",\"79\":\"266E\",\"80\":\"26B5\",\"81\":\"26B5\",\"82\":\"26D8\",\"83\":\"259A\",\"84\":\"48\",\"85\":\"48\",\"86\":\"48\",\"87\":\"33\",\"88\":\"44\",\"89\":\"5555\",\"90\":\"6666\",\"91\":\"0\",\"92\":\"7777\",\"93\":\"0\",\"94\":\"5\",\"95\":\"8888\",\"96\":\"F0\",\"97\":\"50\",\"98\":\"52\",\"99\":\"24EC\",\"100\":\"24EC\",\"101\":\"0\",\"102\":\"0\",\"103\":\"99\",\"104\":\"0\",\"105\":\"10\",\"106\":\"1011\",\"107\":\"0\",\"108\":\"7\",\"109\":\"AA\",\"110\":\"BBBB\",\"111\":\"CC\",\"112\":\"DD\",\"113\":\"34\",\"114\":\"43\",\"115\":\"0\",\"116\":\"C2\",\"117\":\"CE33\",\"118\":\"C1\",\"119\":\"0\",\"120\":\"49\",\"121\":\"45\",\"122\":\"2696\",\"123\":\"78\",\"124\":\"01CE\",\"125\":\"90AB\",\"126\":\"9F\",\"127\":\"0\",\"128\":\"40\",\"129\":\"40\",\"130\":\"BA\",\"131\":\"AB\",\"132\":\"75F5\",\"133\":\"CD\",\"134\":\"DC\",\"135\":\"AE\",\"136\":\"AD\",\"137\":\"410B\",\"138\":\"D4D0\",\"139\":\"4161\",\"140\":\"F441\",\"141\":\"2696\",\"142\":\"C0FA\",\"143\":\"BC\",\"144\":\"1147\",\"145\":\"2035\",\"146\":\"81\",\"147\":\"3200\",\"148\":\"0\",\"149\":\"60\",\"150\":\"7010\",\"151\":\"1\",\"152\":\"2000\",\"153\":\"090D\",\"154\":\"37\",\"155\":\"32\",\"156\":\"30\"}}]}";
		UdsToScale.initialize("C:\\Software\\Data\\Phani\\Technology Learning\\POC_High_Speed_Ingestion\\Code\\ECFG_Parser\\E_2016_10_03_902.pcfg");
	    parseData(message);
	}
	//Write a method with i/p : String
	
	public static void parseData(String message){
		//System.out.println(map.values());
		try{
			JSONObject messageJson=new JSONObject(message);
			System.out.println("Input");
			System.out.println(messageJson);
			//Parse the Samples : array, object
			JSONArray samples=messageJson.getJSONArray("Samples");
			//System.out.println(samples);
			for(int i=0;i<samples.length();i++){
				JSONObject obj = samples.getJSONObject(i);
				JSONObject rawParams=obj.getJSONObject("RawEquipmentParameters");
			//JSON is available
			Iterator<String> keys=rawParams.keys();
			
			while(keys.hasNext()){
				String key = keys.next();
				String val=null;
				String scaledVal=null;
				val= rawParams.getString(key);
				if(val!=null && !val.isEmpty()){
					int paramId = 0;
					//Load properties
					Properties properties = PropertiesLoader.getProperties("hexValues.properties");
					String[] strs81=properties.getProperty("16781881").split(",");
					String[] strs67=properties.getProperty("16782467").split(",");
					String[] strs92=properties.getProperty("16801292").split(",");
					List<String> list81=Arrays.asList(strs81);
					List<String> list67=Arrays.asList(strs67);
					List<String> list92=Arrays.asList(strs92);
					if(list81.contains(val))
						paramId=Integer.parseInt("16781881");
					else if(list67.contains(val))
						paramId=Integer.parseInt("16782467");
					else if(list92.contains(val))
						paramId=Integer.parseInt("16801292");
					else{
						//System.out.println("Hex value out of scope");
						rawParams.put(key, "");
						System.out.println("Outliers: Key: "+key+"; Value: "+rawParams.get(key));
					}
					if(paramId!=0){
						scaledVal=UdsToScale.getScaled(paramId, val);
						System.out.println(scaledVal);	
						rawParams.put(key, scaledVal);
					}
				}else
					System.out.println("---Hex value is null/empty---");
				
				}
			}
			
			//Example for calling getScaled method
			/*String result=UdsToScale.getScaled("16781881", "259A");
			System.out.println(result);*/
			
			JSONArray samples1=messageJson.getJSONArray("Samples");
			//System.out.println(samples);
			for(int i=0;i<samples1.length();i++){
				JSONObject obj = samples1.getJSONObject(i);
				JSONObject rawParams=obj.getJSONObject("RawEquipmentParameters");
				System.out.println("length: "+rawParams.length());
			}
			
			System.out.println("Output");
			System.out.println(messageJson);
			
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
}
