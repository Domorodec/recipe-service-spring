package com.service.recipe.utility;

import com.service.recipe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailConstructor {

  @Autowired
  private Environment env;

  @Autowired
  private TemplateEngine templateEngine;

  public MimeMessagePreparator constructNewUserEmail(User user, String password) {
    Context context = new Context();
    context.setVariable("user", user);
    context.setVariable("password", password);
    String text = templateEngine.process("newUserEmailTemplate", context);

    MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
      @Override
      public void prepare(MimeMessage mimeMessage) throws Exception {
        MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
        email.setPriority(1);
        email.setSubject(user.getEmail());
        email.setSubject("Welcome to recipes");
        email.setText(text, true);
        email.setFrom(new InternetAddress(env.getProperty("support.email")));
      }
    };
    return messagePreparator;
  }

  public MimeMessagePreparator constructUpdateUserProfileEmail(User user) {
    Context context = new Context();
    context.setVariable("password", user.getPassword());
    String text = templateEngine.process("updateUserEmailTemplate", context);
    MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
      @Override
      public void prepare(MimeMessage mimeMessage) throws Exception {
        MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
        email.setPriority(1);
        email.setSubject(user.getEmail());
        email.setSubject("Update password");
        email.setText(text, true);
        email.setFrom(new InternetAddress(env.getProperty("support.email")));
      }
    };
    return messagePreparator;
  }
}
