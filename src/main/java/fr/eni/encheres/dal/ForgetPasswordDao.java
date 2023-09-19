package fr.eni.encheres.dal;

import fr.eni.encheres.bo.ForgetPassword;

public interface ForgetPasswordDao extends DAO<ForgetPassword> {
	ForgetPassword resetPassword(String email );
}
