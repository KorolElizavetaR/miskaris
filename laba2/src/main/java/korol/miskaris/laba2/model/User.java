package korol.miskaris.laba2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "users")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class User {
	@Column (name = "id")
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name = "username")
	@NotEmpty
	@Size (min = 4, max = 30)
	private String username;
	
	@Column (name = "password")
	@NotEmpty
	private String password;
	
	@Column (name = "user_role")
	@NotEmpty
	private String role;
	
	public User (String username, String password)
	{
		this.password = password;
		this.username = username;
	}
	
}
