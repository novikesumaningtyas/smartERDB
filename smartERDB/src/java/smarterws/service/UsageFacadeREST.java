/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarterws.service;

import entities.ApplianceUsage;
import entities.DailyUsage;
import entities.HighestConsumption;
import entities.HourlyPowerUsage;
import entities.totalPowerUsage;
import entities.HourlyView;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import smarterws.Usage;

/**
 *
 * @author USER
 */
@Stateless
@Path("smarterws.usage")
public class UsageFacadeREST extends AbstractFacade<Usage> {

    @PersistenceContext(unitName = "smartERDBPU")
    private EntityManager em;

    public UsageFacadeREST() {
        super(Usage.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Usage entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Usage entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usage find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    //////////////////////////ASSIGNMENT/////////////////////////////////
    
    //New GET method for Task 3
    
    //Task 3.1
    //Usage Electricity ID
    @GET
    @Path("findByUsageid/{usageid}")
        @Produces({"application/json"})
        public List<Usage> findByUsageid(@PathParam("usageid") Integer usageid) 
        {
            Query query = em.createNamedQuery("Usage.findByUsageid");
            query.setParameter("usageid", usageid);
            return query.getResultList();
        }
    
    //Date
    @GET
    @Path("findByDate/{date}")
        @Produces({"application/json"})
        public List<Usage> findByDate(@PathParam("date") String date) throws ParseException 
        {
            Query query = em.createNamedQuery("Usage.findByDate");
            SimpleDateFormat dateParam = new SimpleDateFormat("yyyy-MM-dd");
            Date dateRecord = dateParam.parse(date);
            
            query.setParameter("date", dateRecord);
            return query.getResultList();
        }
        
    //Air Con Usage
    @GET
    @Path("findByAirconusage/{airconusage}")
        @Produces({"application/json"})
        public List<Usage> findByAirconusage(@PathParam("airconusage") Double airconusage) 
        {
            Query query = em.createNamedQuery("Usage.findByAirconusage");
            query.setParameter("airconusage", airconusage);
            return query.getResultList();
        }
        
    //Temperature
    @GET
    @Path("findByTemperature/{temperature}")
        @Produces({"application/json"})
        public List<Usage> findByTemperature(@PathParam("temperature") Double temperature) 
        {
            Query query = em.createNamedQuery("Usage.findByTemperature");
            query.setParameter("temperature", temperature);
            return query.getResultList();
        }   
        
    //Hour Usage
    @GET
    @Path("findByHourusage/{hourusage}")
        @Produces({"application/json"})
        public List<Usage> findByHourusage(@PathParam("hourusage") Integer hourusage) 
        {
            Query query = em.createNamedQuery("Usage.findByHourusage");
            query.setParameter("hourusage", hourusage);
            return query.getResultList();
        }
        
    //Fridge Usage
    @GET
    @Path("findByFridgeusage/{fridgeusage}")
        @Produces({"application/json"})
        public List<Usage> findByFridgeusage(@PathParam("fridgeusage") Double fridgeusage) 
        {
            Query query = em.createNamedQuery("Usage.findByFridgeusage");
            query.setParameter("fridgeusage", fridgeusage);
            return query.getResultList();
        }
    
    //Washing machine usage
    @GET
    @Path("findByWashmachineusage/{washmachineusage}")
        @Produces({"application/json"})
        public List<Usage> findByWashmachineusage(@PathParam("washmachineusage") Double washmachineusage) 
        {
            Query query = em.createNamedQuery("Usage.findByWashmachineusage");
            query.setParameter("washmachineusage", washmachineusage);
            return query.getResultList();
        }
        
    //Task 3.3
    @GET
    @Path("findByResidentPostcodeANDHourusage/{postcode}/{hourusage}")
        @Produces({"application/json"})
        public List<Usage> findByResidentPostcodeANDHourusage (@PathParam("postcode") Integer postcode, 
               @PathParam("hourusage") Integer hourusage)
        {
            TypedQuery<Usage> query = 
                    em.createQuery("Select u FROM Usage u Where u.resid.postcode = :postcode "
                            + "AND u.hourusage = :hourusage", Usage.class);
            
            query.setParameter("postcode", postcode);
            query.setParameter("hourusage", hourusage);
       
            return query.getResultList();
        }
     
    //Task 3.4
    //findByEmailANDAirconusage
    @GET
    @Path("findByEmailANDAirconusage/{email}/{airconusage}")
        @Produces({"application/json"})
        public List<Usage> findByEmailANDAirconusage (@PathParam("email") String email, 
               @PathParam("airconusage") Double airconusage)
        {
            Query query = em.createNamedQuery("Usage.findByEmailANDAirconusage");
            query.setParameter("email", email);
            query.setParameter("airconusage", airconusage);
        
            return query.getResultList();
        }
        
     //Task 4.1
     //Find hourly power usage of the appliance specified
    @GET
    @Path("findAllApplianceUsage/{resid}/{date}/{hourusage}")
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        public List<ApplianceUsage> findAllApplianceUsage (@PathParam("resid") Integer resid, 
               @PathParam("date") String date, @PathParam("hourusage") Integer hourusage) throws ParseException
        {
            TypedQuery<Object[]> query = 
                    em.createQuery("Select u.fridgeusage, u.airconusage, u.washmachineusage  "
                            + "FROM Usage AS u Where u.resid.resid = :resid AND u.date = :date AND u.hourusage = :hourusage", Object[].class);
            
            SimpleDateFormat dateParam = new SimpleDateFormat("yyyy-MM-dd");
            Date dateRecord = dateParam.parse(date);
            
            query.setParameter("resid", resid);
            query.setParameter("date", dateRecord);
            query.setParameter("hourusage", hourusage);
            
            List<Object[]> queryList = query.getResultList();
            List<ApplianceUsage> finalResult =  new ArrayList<>();
        
             queryList.forEach((row) -> {
                finalResult.add(new ApplianceUsage((BigDecimal) row[0], (BigDecimal) row[1], (BigDecimal) row[2]));
        });     
            return finalResult;
        }
        
        
        @GET
        @Path("findApplianceUsage/{resid}/{appliance}/{date}/{hourusage}") 
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        public Object findApplianceUsage(@PathParam("resid") int resid, @PathParam("appliance") String appliance,@PathParam("date") String date, 
                @PathParam("hourusage") int hourusage) throws ParseException
        {
            
            List<ApplianceUsage> result =  findAllApplianceUsage(resid, date, hourusage);
            JsonObject applianceElUsage = Json.createObjectBuilder().build();
            
            if(appliance.equalsIgnoreCase("fridge") == true)
            {
                for(ApplianceUsage row : result)
                {
                    applianceElUsage = Json.createObjectBuilder()
                    .add("fridge", row.getFridge()).build();
                }
                return applianceElUsage;
            }
           
            else
                if(appliance.equalsIgnoreCase("washing machine") == true || appliance.equalsIgnoreCase("washingmachine") == true)
                {
                    for(ApplianceUsage row : result)
                    {
                        applianceElUsage = Json.createObjectBuilder()
                        .add("washingmachine", row.getWashingMachine()).build();
                    }
                    return applianceElUsage;
                }
            
           for(ApplianceUsage row : result)
           {
            applianceElUsage = Json.createObjectBuilder()
            .add("aircon", row.getAirCon()).build();
           }
           
           return applianceElUsage;
            
        }
        
     //Task 4.2
     //Find hourly power usage of all three appliances for that date and time
    @GET
    @Path("findResidentUsageForAllThreeAppliances/{resid}/{date}/{hourusage}") 
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        public List<HourlyPowerUsage> findResidentUsageForAllThreeAppliances (@PathParam("resid") Integer resid, 
               @PathParam("date") String date, @PathParam("hourusage") Integer hourusage) throws ParseException
        {
            TypedQuery<Object[]> query = 
                    em.createQuery("Select u.fridgeusage, u.washmachineusage, u.airconusage FROM Usage AS u "
                            + "Where u.resid.resid = :resid AND u.date = :date AND u.hourusage = :hourusage", Object[].class);
            
            SimpleDateFormat dateParam = new SimpleDateFormat("yyyy-MM-dd");
            Date dateRecord = dateParam.parse(date);
            
            query.setParameter("resid", resid);
            query.setParameter("date", dateRecord);
            query.setParameter("hourusage", hourusage);
            
            List<Object[]> queryList = query.getResultList();
            List<HourlyPowerUsage> finalResult =  new ArrayList<>();
            
            queryList.forEach((row) -> {
                finalResult.add(new HourlyPowerUsage((BigDecimal) row[0], (BigDecimal) row[1], (BigDecimal) row[2]));
        });     
            return finalResult;
        }
        
    @GET
    @Path("findResidentPowerUsageForAllThreeAppliances") 
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        public List<HourlyPowerUsage> findResidentPowerUsageForAllThreeAppliances () 
        {
            TypedQuery<Object[]> q = em.createQuery("SELECT u.fridgeusage, u.washmachineusage, u.airconusage FROM Usage AS u", Object[].class);
            
            List<Object[]> queryList = q.getResultList();
            List<HourlyPowerUsage> finalResult =  new ArrayList<>();
            
            for (Object[] row : queryList) 
            {
                finalResult.add(new HourlyPowerUsage((BigDecimal) row[0], (BigDecimal) row[1], (BigDecimal) row[2]));
            }     
            return finalResult;
        }
        
        //Task 4.3
        //Find total usage from date and hour
        @GET
        @Path("findTotalUsageForAllResident/{date}/{hourusage}") 
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        public List<totalPowerUsage> findTotalUsageForAllResident (@PathParam("date") String date, @PathParam("hourusage") Integer hourusage) throws ParseException
        {
            TypedQuery<Object[]> query = 
                    em.createQuery("Select u.fridgeusage, u.airconusage, u.washmachineusage, u.resid.resid, u.resid.address, u.resid.postcode  FROM Usage AS u Where u.date = :date AND u.hourusage = :hourusage", Object[].class);
            
            SimpleDateFormat dateParam = new SimpleDateFormat("yyyy-MM-dd");
            Date dateRecord = dateParam.parse(date);
            
            query.setParameter("date", dateRecord);
            query.setParameter("hourusage", hourusage);
            
            List<Object[]> queryList = query.getResultList();
            List<totalPowerUsage> finalResult =  new ArrayList<>();
            
            queryList.forEach((row) -> {
                finalResult.add(new totalPowerUsage((BigDecimal) row[0], (BigDecimal) row[1], (BigDecimal) row[2], (int) row[3], (String) row[4], (int) row[5]));
        });     
            return finalResult;
        }
        
        @GET
        @Path("findTotalUsageForAllResident") 
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        public List<totalPowerUsage> findTotalUsageForAllResident ()
        {
            TypedQuery<Object[]> query = 
                    em.createQuery("Select u.fridgeusage, u.airconusage, u.washmachineusage, u.resid.resid, u.resid.address, u.resid.postcode FROM Usage AS u", Object[].class);
           
            
            List<Object[]> queryList = query.getResultList();
            List<totalPowerUsage> finalResult =  new ArrayList<>();
            
            queryList.forEach((row) -> {
                finalResult.add(new totalPowerUsage((BigDecimal) row[0], (BigDecimal) row[1], (BigDecimal) row[2], (int) row[3], (String) row[4], (int) row[5]));
        });     
            return finalResult;
        }
        
        //Task 4.4
        //Find The Highest Hourly Power Consumption
        @GET
        @Path("findHighestHourlyPowerConsumption/{resid}") 
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        public Object findHighestHourlyPowerConsumption (@PathParam("resid") int resid)
        {
            TypedQuery<Object[]> query = 
                    em.createQuery("Select u.fridgeusage, u.airconusage, u.washmachineusage, u.date, u.hourusage FROM Usage AS u WHERE u.resid.resid = :resid", Object[].class);
                           
            query.setParameter("resid", resid);
           
            List<Object[]> queryList = query.getResultList();
            List<HighestConsumption> finalResult =  new ArrayList<>();
            
            for (Object[] row : queryList) 
            {
                finalResult.add(new HighestConsumption((BigDecimal) row[0], (BigDecimal) row[1], (BigDecimal) row[2], (Date) row[3], (int) row[4]));
            }
            
            BigDecimal max = new BigDecimal("0");
            for(HighestConsumption row : finalResult)
            {
               BigDecimal usage = row.getUsage();
               if(usage.compareTo(max) > 0)
               {
                   max = usage;
               }             
            }
            
            List<HighestConsumption> highResult =  new ArrayList<>();
             for(HighestConsumption row : finalResult)
             {
                 if(row.getUsage().compareTo(max) == 0)
                 {
                     highResult.add(row);
                 }
             }
            
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            JsonObject highestConsumptionUsage = Json.createObjectBuilder().build();
            
            for(HighestConsumption row : highResult)
            {
                highestConsumptionUsage = Json.createObjectBuilder()
                .add("date", row.getDatestring())
                .add("time", row.getTime())
                .add("usage", row.getUsage())
                .build();
                arrayBuilder.add(highestConsumptionUsage);
            }
            
            JsonArray jArray = arrayBuilder.build();
            return jArray;
        }
        
        //Task5
        //Task 5.1 Daily Usage of Appliances
        
         @GET
        @Path("findDailyUsageofAppliances") 
        @Produces({"application/json"})
        public List<DailyUsage> findDailyUsageofAppliances ()
        {
            TypedQuery<Object[]> query = 
                    em.createQuery("Select u.resid.resid, u.fridgeusage, u.airconusage, u.washmachineusage FROM Usage AS u", Object[].class);
           
            
            List<Object[]> queryList = query.getResultList();
            List<DailyUsage> finalResult =  new ArrayList<>();
            
            queryList.forEach((row) -> {
                finalResult.add(new DailyUsage((int) row[0], (BigDecimal) row[1], (BigDecimal) row[2], (BigDecimal) row[3]));
        });
             
           BigDecimal dailyFridgeUsage = new BigDecimal("0");
           
           for(DailyUsage row : finalResult)
           {
               dailyFridgeUsage = dailyFridgeUsage.add(row.getFridge());
           }
           
           List<DailyUsage> finalResult2 =  new ArrayList<>();
           finalResult2.add(new DailyUsage(9999, dailyFridgeUsage,dailyFridgeUsage,dailyFridgeUsage));
            
            return finalResult2;
        }
        
        
        //Task 5.1 Daily Usage of Appliances find by resident ID and DATE
        @GET
        @Path("findDailyUsageofAppliances/{resid}/{date}") 
        @Produces({"application/json"})
        public List<DailyUsage> findDailyUsageofAppliances (@PathParam("resid") int resid, 
                @PathParam("date") String date) throws ParseException
        {
            TypedQuery<Object[]> query = 
                    em.createQuery("Select u.resid.resid, u.fridgeusage, u.airconusage, "
                            + "u.washmachineusage FROM Usage AS u WHERE u.resid.resid = :resid "
                            + "AND u.date = :date", Object[].class);
                    
            SimpleDateFormat dateParam = new SimpleDateFormat("yyyy-MM-dd");
            Date dateRecord = dateParam.parse(date);
            
            query.setParameter("resid", resid);
            query.setParameter("date", dateRecord);
           
            List<Object[]> queryList = query.getResultList();
            List<DailyUsage> finalResult =  new ArrayList<>();
            
            queryList.forEach((row) -> {
                finalResult.add(new DailyUsage((int) row[0], (BigDecimal) row[1], 
                        (BigDecimal) row[2], (BigDecimal) row[3]));
        }); 
           
           BigDecimal dailyFridgeUsage = new BigDecimal("0");
           BigDecimal dailyAirConUsage = new BigDecimal("0");
           BigDecimal dailyWashingMachineUsage = new BigDecimal("0");
           
           for(DailyUsage row : finalResult)
           {
               dailyFridgeUsage = dailyFridgeUsage.add(row.getFridge());
               dailyAirConUsage = dailyAirConUsage.add(row.getAirCon());
               dailyWashingMachineUsage = dailyWashingMachineUsage.add(row.getWashingMachine());
           }
          
           List<DailyUsage> finalResult2 =  new ArrayList<>();
           finalResult2.add(new DailyUsage(resid, dailyFridgeUsage,dailyAirConUsage,dailyWashingMachineUsage));
            
            return finalResult2;
        }
        
        
       //jsonObject
        @GET
        @Path("findDailyUsageofAppliancesInJson/{resid}/{date}") 
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        public Object findDailyUsageofAppliancesInJson (@PathParam("resid") int resid, 
                @PathParam("date") String date) throws ParseException
        {   
            List<DailyUsage> result =  findDailyUsageofAppliances(resid, date);
            
            JsonObject dailyElectricalUsage = Json.createObjectBuilder().build();

            for(DailyUsage row : result)
            {
                dailyElectricalUsage = Json.createObjectBuilder()
                .add("resid", row.getResId())
                .add("fridge", row.getFridge())
                .add("aircon", row.getAirCon())
                .add("washingmachine", row.getWashingMachine()).build();
            }
            return dailyElectricalUsage;
        }
        
        //Task 5.2
        //Hourly Usage
        @GET
        @Path("findElectricalUsageForResident/{resid}/{date}") 
        @Produces({MediaType.APPLICATION_JSON})
        public Object findElectricalUsageForResident (@PathParam("resid") int resid, 
                @PathParam("date") String date) throws ParseException
        {
            TypedQuery<Object[]> query = 
                    em.createQuery("Select u.resid.resid, u.fridgeusage, u.airconusage, "
                            + "u.washmachineusage, u.temperature, u.date, u.hourusage "
                            + "FROM Usage AS u WHERE u.resid.resid = :resid AND u.date = :date", Object[].class);
           
            SimpleDateFormat dateParam = new SimpleDateFormat("yyyy-MM-dd");
            Date dateRecord = dateParam.parse(date);
            
            query.setParameter("resid", resid);
            query.setParameter("date", dateRecord);
           
            List<Object[]> queryList = query.getResultList();
            List<HourlyView> finalResult =  new ArrayList<>();
            
            for (Object[] row : queryList) 
            {
                finalResult.add(new HourlyView((int) row[0], (BigDecimal) row[1], 
                        (BigDecimal) row[2], (BigDecimal) row[3], (BigDecimal) row[4], (Date) row[5], (int) row[6] ));
            }     
                     
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(); 
            JsonObject usageHourly;
            for (HourlyView row : finalResult) 
            {
                usageHourly = Json.createObjectBuilder()
                .add("resid", resid )
                .add("usage", row.getUsage() )
                .add("temperature",row.getTemperature() )
                .add("date", row.getDatestring() )
                .add("time", row.getTime())
                .build();
                arrayBuilder.add(usageHourly);
            }
            JsonArray jArray = arrayBuilder.build();
            return jArray;
        }
        
        
        @GET
        @Path("findElectricalUsageForResident") 
        @Produces({MediaType.APPLICATION_JSON})
        public Object findElectricalUsageForResident ()
        {
            TypedQuery<Object[]> query = 
                    em.createQuery("Select u.resid.resid, u.fridgeusage, u.airconusage, u.washmachineusage, u.temperature, u.date, u.hourusage FROM Usage AS u", Object[].class);
           
            List<Object[]> queryList = query.getResultList();
            List<HourlyView> finalResult =  new ArrayList<>();
            
            for (Object[] row : queryList) 
            {
                finalResult.add(new HourlyView((int) row[0], (BigDecimal) row[1], (BigDecimal) row[2], (BigDecimal) row[3], (BigDecimal) row[4], (Date) row[5], (int) row[6] ));
            }     
                     
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(); 
            JsonObject usageHourly;
            for (HourlyView row : finalResult) 
            {
                usageHourly = Json.createObjectBuilder()
                .add("resid", row.getResId() )
                .add("usage", row.getUsage() )
                .add("temperature",row.getTemperature() )
                .add("date", row.getDatestring() )
                .add("time", row.getTime())
                .build();
                arrayBuilder.add(usageHourly);
            }
        
            JsonArray jArray = arrayBuilder.build();
            return jArray;
        }
        
        //Task 5.2 The final result for Hourly/Daily Usage
        @GET
        @Path("findElectricalUsagebyView/{resid}/{date}/{viewmode}") 
        @Produces({MediaType.APPLICATION_JSON})
        public Object findElectricalUsagebyView(@PathParam("resid") int resid, @PathParam("date") String date, 
                @PathParam("viewmode") String mode) throws ParseException
        {
            if(mode.equalsIgnoreCase("daily") == true)
            {
                JsonObject dailyViewElectricalUsage = (JsonObject) findElectricalDailyViewUsageForResident(resid,date);
                return dailyViewElectricalUsage;
            }
            
            JsonArray jArray = (JsonArray) findElectricalUsageForResident(resid,date);
            return jArray;
        }
        
        
        //DailyUsage
        @GET
        @Path("findElectricalDailyViewUsageForResident/{resid}/{date}") 
        @Produces({MediaType.APPLICATION_JSON})
        public Object findElectricalDailyViewUsageForResident(@PathParam("resid") int resid, 
                @PathParam("date") String date) throws ParseException
        {
            TypedQuery<Object[]> query = 
                    em.createQuery("Select u.resid.resid, u.fridgeusage, u.airconusage, "
                            + "u.washmachineusage, u.temperature, u.date, u.hourusage "
                            + "FROM Usage AS u WHERE u.resid.resid = :resid AND u.date = :date", Object[].class);
           
            SimpleDateFormat dateParam = new SimpleDateFormat("yyyy-MM-dd");
            Date dateRecord = dateParam.parse(date);
            
            query.setParameter("resid", resid);
            query.setParameter("date", dateRecord);
            
            List<Object[]> queryList = query.getResultList();
            List<HourlyView> finalResult =  new ArrayList<>();
            
            for (Object[] row : queryList) 
            {
                finalResult.add(new HourlyView((int) row[0], (BigDecimal) row[1], (BigDecimal) row[2], 
                        (BigDecimal) row[3], (BigDecimal) row[4], (Date) row[5], (int) row[6] ));
            }     
            
           BigDecimal dailyFridgeUsage = new BigDecimal("0");
           BigDecimal dailyAirConUsage = new BigDecimal("0");
           BigDecimal dailyWashingMachineUsage = new BigDecimal("0");
           BigDecimal dailyTemp = new BigDecimal("0");
           BigDecimal totalDaily;
           BigDecimal oneday = new BigDecimal("24");
           BigDecimal dailyAverageTemp ;
  
           for(HourlyView row : finalResult)
           {
               dailyFridgeUsage = dailyFridgeUsage.add(row.getFridge());
               dailyAirConUsage = dailyAirConUsage.add(row.getAirCon());
               dailyWashingMachineUsage = dailyWashingMachineUsage.add(row.getWashingMachine());
               dailyTemp = dailyTemp.add(row.getTemperature());
           }
           
           totalDaily = dailyFridgeUsage.add(dailyAirConUsage);
           totalDaily = totalDaily.add(dailyWashingMachineUsage);
           dailyAverageTemp = dailyTemp.divide(oneday,2, BigDecimal.ROUND_HALF_UP);
            
            JsonObject dailyViewElectricalUsage = Json.createObjectBuilder().build();
            for (HourlyView row : finalResult) 
            {
                dailyViewElectricalUsage = Json.createObjectBuilder()
                .add("resid", resid )
                .add("usage", totalDaily )
                .add("temperature",dailyAverageTemp )
                .build();
            }
            return dailyViewElectricalUsage;
        }
        
        //Daily without parameter
        @GET
        @Path("findElectricalDailyUsageForResident") 
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        public Object findElectricalDailyUsageForResident()
        {
            TypedQuery<Object[]> query = 
                    em.createQuery("Select u.resid.resid, u.fridgeusage, u.airconusage, u.washmachineusage, u.temperature, u.date, u.hourusage FROM Usage AS u", Object[].class);
           
            
            List<Object[]> queryList = query.getResultList();
            List<HourlyView> finalResult =  new ArrayList<>();
            
            for (Object[] row : queryList) 
            {
                finalResult.add(new HourlyView((int) row[0], (BigDecimal) row[1], (BigDecimal) row[2], (BigDecimal) row[3], (BigDecimal) row[4], (Date) row[5], (int) row[6] ));
            }     
            
           BigDecimal dailyFridgeUsage = new BigDecimal("0");
           BigDecimal dailyAirConUsage = new BigDecimal("0");
           BigDecimal dailyWashingMachineUsage = new BigDecimal("0");
           BigDecimal totalDaily;
           
           for(HourlyView row : finalResult)
           {
               dailyFridgeUsage = dailyFridgeUsage.add(row.getFridge());
               dailyAirConUsage = dailyAirConUsage.add(row.getAirCon());
               dailyWashingMachineUsage = dailyWashingMachineUsage.add(row.getWashingMachine());
           }
           
           totalDaily = dailyFridgeUsage.add(dailyAirConUsage);
           totalDaily = totalDaily.add(dailyWashingMachineUsage);
            
                     
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(); 
            JsonObject usageHourly;
            for (HourlyView row : finalResult) 
        {
            usageHourly = Json.createObjectBuilder()
            .add("resid", row.getResId() )
            .add("usage", totalDaily )
            .add("temperature",row.getTemperature() )
            .build();
            arrayBuilder.add(usageHourly);
        }
        
        JsonArray jArray = arrayBuilder.build();
        return jArray;
        }
        
         //////////////////////////////////////////////////////////////////
        
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usage> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usage> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
