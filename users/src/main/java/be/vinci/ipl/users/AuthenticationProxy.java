package be.vinci.ipl.users;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;

@Repository
@FeignClient(name = "videos")
public interface AuthenticationProxy {

}
