package ir.ord.application.Convertor;

import ir.ord.application.dal.entities.FeedBackObject;
import ir.ord.application.dto.ComboElementDto;
import ir.ord.application.dto.FeedbackObjectDto;
import ir.ord.application.dto.protoes.ParameterProto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by vahid on 10/3/17.
 */
@ApplicationScoped
public class FeedBackConverter {

    @Inject
    OptionObjectConverter optionObjectConverter;

    public FeedbackObjectDto getDto(FeedBackObject feedBackObject){
        if (feedBackObject == null)
            return null;
        FeedbackObjectDto feedbackObjectDto = new FeedbackObjectDto();
        feedbackObjectDto.setOpinion(feedBackObject.getOpinion());
        feedbackObjectDto.setSatisfactionLevel(feedBackObject.getSatisfactionLevel());
        feedbackObjectDto.setComboElementList(optionObjectConverter.getDtoList(feedBackObject.getOptionObjectList()));
        return feedbackObjectDto;
    }

    public FeedBackObject getEntity(FeedbackObjectDto feedbackObjectDto){
        if (feedbackObjectDto == null)
            return null;
        FeedBackObject feedBackObject = new FeedBackObject();
        feedBackObject.setSatisfactionLevel(feedbackObjectDto.getSatisfactionLevel());
        feedBackObject.setOpinion(feedbackObjectDto.getOpinion());
        feedBackObject.setOptionObjectList(optionObjectConverter.getEntityList(feedbackObjectDto.getComboElementList()));
        return  feedBackObject;
    }

    public static FeedbackObjectDto getDtoFromBuilder(ParameterProto.FeedbackObject.Builder feedbackBuilder) {
        if (feedbackBuilder == null)
            return null;
        FeedbackObjectDto feedbackObjectDto = new FeedbackObjectDto();
        feedbackObjectDto.setOpinion(feedbackBuilder.getOpinion());
        feedbackObjectDto.setSatisfactionLevel(feedbackBuilder.getSatisfactionLevel());
        feedbackObjectDto.setComboElementList(
                OptionObjectConverter.getDtoFromBuilderList(feedbackBuilder.getComboElementListBuilderList())
        );
        return feedbackObjectDto;
    }
}
