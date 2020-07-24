package hu.zerotohero.verseny.crud.util;

public enum JobTypePair {
    COOK(EmployeeJob.COOK, EquipmentType.OVEN),
    CASHIER(EmployeeJob.CASHIER, EquipmentType.CASH_REGISTER);

    EmployeeJob job;
    EquipmentType equipment;
    JobTypePair(EmployeeJob employeeJob, EquipmentType equipmentType) {
        this.job = employeeJob;
        this.equipment = equipmentType;
    }

    public static EquipmentType typeByJob(EmployeeJob job) {
        for (JobTypePair jobTypePair : values()) {
            if (jobTypePair.job.equals(job)) {
                return jobTypePair.equipment;
            }
        }
        return null;
    }
}
