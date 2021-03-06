package application_target_list.core.services;

import application_target_list.core.database.Database;
import application_target_list.core.requests.DeleteTargetRequest;
import application_target_list.core.responses.CoreError;
import application_target_list.core.responses.DeleteTargetResponse;
import application_target_list.core.validators.DeleteTargetValidator;

import java.util.List;

public class DeleteTargetService {
    private final Database database;
    private final DeleteTargetValidator validator;

    public DeleteTargetService(Database database, DeleteTargetValidator validator){
        this.database = database;
        this.validator = validator;
    }

    public DeleteTargetResponse execute(DeleteTargetRequest request){
        List<CoreError> errors = validator.validate(request, database);

        if (!database.isIdInTargetList(request.getTargetIdToDelete())){
            errors.add(new CoreError("Target ID;","no target with that ID"));
        }

        if (!errors.isEmpty()) {
            return new DeleteTargetResponse(errors);
        }

        database.deleteTarget(request.getTargetIdToDelete());
        return new DeleteTargetResponse(request.getTargetIdToDelete());
    }

}
