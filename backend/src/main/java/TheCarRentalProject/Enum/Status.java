package TheCarRentalProject.Enum;

public enum Status {

        Status_Free("Status_Free"),
        Status_Busy("Status_Busy");

        private final String status;

        Status(String status) {
            this.status= status;
        }
        public String getStatus(){
            return this.status;
        }
    }


