package edu.cnm.deepdive.albuquirky.service;

import edu.cnm.deepdive.albuquirky.model.dao.ProfileRepository;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.Collection;
import java.util.Collections;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class ProfileService implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

  private final ProfileRepository profileRepository;

  @Autowired
  public ProfileService(ProfileRepository profileRepository) {
    this.profileRepository = profileRepository;
  }

  public Profile getOrCreate(String oauth, String username, String email) {
    return profileRepository.findFirstByOauth(oauth)
        .orElseGet(() -> {
          Profile profile = new Profile();
          profile.setOauth(oauth);
          profile.setUsername(username);
          profile.setEmail(email);
          return profileRepository.save(profile);
        });
  }

  @Override
  public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
    return null;
  }
}
