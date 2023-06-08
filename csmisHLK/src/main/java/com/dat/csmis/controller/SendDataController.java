package com.dat.csmis.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.exolab.castor.types.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dat.csmis.ScheculerController.MailScheeculer;
import com.dat.csmis.entity.DataObj;
import com.dat.csmis.entity.DateListEntity;
import com.dat.csmis.entity.EmpDoorlog;
import com.dat.csmis.entity.EmployeeEntity;
import com.dat.csmis.entity.HolidaysEntity;
import com.dat.csmis.entity.MeatList;
import com.dat.csmis.entity.RegisterInfo;
import com.dat.csmis.entity.UserViewEntity;
import com.dat.csmis.model.DailyDatePrice;
import com.dat.csmis.model.DailyRegister;
import com.dat.csmis.model.WeeklyLists;
import com.dat.csmis.model.WeeklyPrice;
import com.dat.csmis.service.DailyEatViewService;
import com.dat.csmis.service.DateListEntityService;
import com.dat.csmis.service.DoorLogAllViewSer;
import com.dat.csmis.service.EmployeeService;
import com.dat.csmis.service.HolidayService;
import com.dat.csmis.service.MailSer;
import com.dat.csmis.service.MeatListService;
import com.dat.csmis.service.RegisterSer;
import com.dat.csmis.service.RegisterService;
import com.dat.csmis.service.UserViewService;
import com.dat.csmis.service.WeeklyHistoryService;

@RestController
public class SendDataController {
    
    @Autowired private RegisterService regSer;
	@Autowired private EmployeeService empser;
    @Autowired private RegisterSer registerSer;
	@Autowired private HolidayService holidayService;
	@Autowired private MeatListService meatSerive;
	@Autowired private UserViewService userViewService;
	@Autowired private DoorLogAllViewSer doorLogAllViewSer;
	@Autowired private DailyEatViewService dailyEatViewService;
	@Autowired private WeeklyHistoryService weeklyPrice;
	@Autowired private DateListEntityService datelistEntityService;
	@Autowired private MailScheeculer mailScheeculer;

    @PostMapping("/myDaDa")
	@CrossOrigin
	public void saveData(@RequestBody RegisterInfo registerInfo){
    	System.out.println("testing testing  daf "+registerInfo);
		registerSer.saveData(registerInfo);


		
	}

	@GetMapping("/checkData/{id}")
	public boolean checkUserInfo(@PathVariable("id") String userId){
		EmployeeEntity emp = empser.getOnestaffid(userId);
		
		System.out.println("user exists in register info : "+registerSer.checkUserStaff(emp.getStaffId()));

		return registerSer.checkUserStaff(emp.getStaffId());
	}

	@GetMapping("/getRegisterEmpwithMail/{id}")
	public RegisterInfo getRegisterEmpwithmail(@PathVariable("id") String userid){
		EmployeeEntity emp = empser.getOnestaffid(userid);
		

		return registerSer.getAuser(emp.getStaffId());
	}

	@GetMapping("/getAllHolidays")
	public List<HolidaysEntity> getAllHolidays(){

		return holidayService.getAll();
	}

	@GetMapping("/getAllMeatList")
	public List<MeatList> getAllMeats(){

		return meatSerive.getmeats();
	}

	@GetMapping("/getAUser/{id}")
	public RegisterInfo getAuser(@PathVariable("id")String userid){
		
		// EmployeeEntity emp = empser.getOneEmp(userid);

		return registerSer.getAuser(userid);
	}

	@GetMapping("/getRegisterEatInfo/{staffId}")
	public List<UserViewEntity> getRegisterInfoLunch(@PathVariable("staffId")String staffid){

		return userViewService.getUserLunchInfo(staffid);
	}

	
// 	@PostMapping("/user/getuserhistoryWeekly")
//   public List<EmpDoorlog> getHistory(@RequestBody DataObj myobj){

// 	return doorlogViewRepo.getDoorlogWeeklyLists(myobj.getDoorlog(), myobj.getStartdate(), myobj.getEnddate());

//   }

  @GetMapping("/user/getuserhistoryMonthly")
  public List<EmpDoorlog> getHistoryMonthly(String doorlog,String monthdate ){

	return doorLogAllViewSer.getUserMonthlyHistory(doorlog, monthdate);

  }

  @GetMapping("/user/getWeeklyRestaurantInfo")
  public WeeklyPrice getWeekResInfo(String startdate,String enddate){

	return weeklyPrice.getWeekPrice(startdate, enddate);
  }
  
  @GetMapping("/getallDailyEatView")
  public List<DailyRegister> getdailyeat(String userId){

	return dailyEatViewService.getAll(userId);
  }

  @GetMapping("/user/getOneDailyEatView")
  public List<DailyRegister> getOnedailyeat(String doorlog,String month){

	return dailyEatViewService.getOne(doorlog, month);
  }

  @GetMapping("/user/getMonthly")
  public List<DailyDatePrice> getdailydata(String userId,String month){

	return dailyEatViewService.getMonthlyData(userId,month);
  }


  @GetMapping("/user/sendMonthlyMail")
  public String sendMonthlyMail(){

	try {
		mailScheeculer.sendAllEmployeeMonthly();
		return "send success!!";
		
	} catch (Exception e) {
		// TODO: handle exception

		return " send mail fail!!";
	}

  }


  @GetMapping("/sendRegister")
  public String sendmailger(){

	mailScheeculer.sendRegisterDayMailAll();
	return "send :: !!";
  }

}
