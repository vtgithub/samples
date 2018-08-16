package ir.ord.application.biz_layer.biz;

import ir.ord.application.Convertor.TimePeriodConvertor;
import ir.ord.application.accessories.CustomDate;
import ir.ord.application.accessories.Helper;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dal.dao.TimePeriodDao;
import ir.ord.application.dal.entities.TimePeriodEntity;
import ir.ord.application.dto.TimePeriodDto;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by vahid on 6/28/17.
 */
@Stateless
public class TimePeriodBiz {

    @Inject
    private TimePeriodDao timePeriodDao;
    @Inject
    private TimePeriodConvertor timePeriodConvertor;

    public List<TimePeriodDto> getTimePeriodList() throws DaoException {
        List<TimePeriodEntity> timePeriodEntityList = timePeriodDao.getAll();
        List<TimePeriodDto> timePeriodDtoList = timePeriodConvertor.getTimePeriodDtoList(timePeriodEntityList);
        return timePeriodDtoList;
    }


    public List<TimePeriodDto> getCustomTimePeriodList(long baseTime, int futureDays, long deliveryTime) throws DaoException, CustomValidationException {
        CustomDate customDate = Helper.Calendar.getCustomDateFromMillies(baseTime);
        List<TimePeriodEntity> timePeriodEntityList = timePeriodDao.getCustomTimePeriodList(
                customDate.getDayOfWeek().intValue(), (customDate.getDayOfWeek()+futureDays)%7);

        timePeriodEntityList = getBiasedTimePeriodListFromBaseTime(
                customDate.getDayOfWeek().intValue(),
                (customDate.getMinute() == 0)?customDate.getHour():customDate.getHour()+1,
                timePeriodEntityList);
        List<TimePeriodDto> timePeriodDtoList = timePeriodConvertor.getTimePeriodDtoList(timePeriodEntityList);
        Long customDayBaseTimeMillies = Helper.Calendar.getCustomDayBaseTimeMillies(baseTime);
        addBaseTimeToTimePeriodList(customDate.getDayOfWeek().intValue(), customDayBaseTimeMillies, timePeriodDtoList, deliveryTime);
        return timePeriodDtoList;
    }

    private void addBaseTimeToTimePeriodList(int weekDay, Long customDayBaseTimeMillies, List<TimePeriodDto> timePeriodDtoList, long deliveryTime) throws DaoException, CustomValidationException {
        if (timePeriodDtoList == null || timePeriodDtoList.get(0) == null)
            throw new DaoException();
        int numOfWeekDayDif = 0;
//        Long currentTime = Helper.getCurrentTime();

        for (Iterator<TimePeriodDto> iterator = timePeriodDtoList.iterator(); iterator.hasNext();){
            TimePeriodDto timePeriodDto = iterator.next();
            if (timePeriodDto.getWeekDay() == weekDay )
                timePeriodDto.setBaseTime(customDayBaseTimeMillies);
            else {
                if (timePeriodDto.getWeekDay() > weekDay){
                    numOfWeekDayDif = timePeriodDto.getWeekDay() - weekDay;
                }else {
                    numOfWeekDayDif = (7- weekDay)+(timePeriodDto.getWeekDay());
                }
                timePeriodDto.setBaseTime(customDayBaseTimeMillies + (numOfWeekDayDif*Helper.getOneDayMiliSeconds()));
            }
            //TODO remove this comment

            long calculatedTime = timePeriodDto.getBaseTime() + (timePeriodDto.getFromTime() * Helper.getOneHourMiliSeconds());
//            if (deliveryTime  < currentTime+Helper.ChangeTime.getBaseTimeAppend()){
//                throw new CustomValidationException(ValidationMessages.deliveryTimeExpired);
//            }
//            if ( (calculatedTime <  Math.min(deliveryTime, currentTime+Helper.ChangeTime.getBaseTimeAppend())))
            if ( (calculatedTime <  customDayBaseTimeMillies) )
                iterator.remove();
        }
    }

    private List<TimePeriodEntity> getBiasedTimePeriodListFromBaseTime(int dayOfWeek, int hour, List<TimePeriodEntity> timePeriodEntityList) {

        List<TimePeriodEntity> biasedTimePeriodEntityList = new ArrayList<TimePeriodEntity>();
        for (TimePeriodEntity timePeriodEntity : timePeriodEntityList) {
            if ((timePeriodEntity.getWeekDay() == dayOfWeek) && (timePeriodEntity.getFromTime() >= hour)){
                biasedTimePeriodEntityList.add(timePeriodEntity);
            }else if (timePeriodEntity.getWeekDay() != dayOfWeek){
                biasedTimePeriodEntityList.add(timePeriodEntity);
            }
        }
        return biasedTimePeriodEntityList;
    }
}
