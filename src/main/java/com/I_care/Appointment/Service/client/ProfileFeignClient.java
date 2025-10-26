package com.I_care.Appointment.Service.client;

import com.I_care.Appointment.Service.dto.DoctorDTO;
import com.I_care.Appointment.Service.dto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "Profile-Service", url = "${PROFILE_SERVICE_URL}", configuration = FeignClientInterceptor.class)
public interface ProfileFeignClient {

    @GetMapping("profile/patient/get/{id}")
    public PatientDTO getPatientById(@PathVariable Long id);

    @GetMapping("profile/doctor/get/{id}")
    public DoctorDTO getDoctorById(@PathVariable Long id);

    @GetMapping("/profile/doctor/exists/{id}")
    public Boolean doctorExists(@PathVariable Long id);

    @GetMapping("/profile/patient/exists/{id}")
    public Boolean patientExists(@PathVariable Long id);
}
