package br.com.dio.diodesafioperson.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private Long id;

    @NotEmpty
    @Size(min = 3, max = 100)
    private String firstName;

    @NotEmpty
    @Size(min = 3, max = 100)
    private String lastName;

    @NotEmpty
    @CPF
    private String cpf;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @Valid
    @NotEmpty
    private List<PhoneDTO> phones;

}
