package com.maisamicoski.projdevweb;

import com.maisamicoski.projdevweb.model.Aluno;
import com.maisamicoski.projdevweb.model.Professor;
import com.maisamicoski.projdevweb.model.Turma;
import com.maisamicoski.projdevweb.repository.AlunoRepository;
import com.maisamicoski.projdevweb.repository.InscricaoRepository;
import com.maisamicoski.projdevweb.repository.ProfessorRepository;
import com.maisamicoski.projdevweb.repository.TurmaRepository;
import com.maisamicoski.projdevweb.service.AlunoService;
import com.maisamicoski.projdevweb.service.InscricaoService;
import com.maisamicoski.projdevweb.service.ProfessorService;
import com.maisamicoski.projdevweb.service.TurmaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ProjdevwebApplication implements CommandLineRunner {


	private final AlunoRepository alunoRepository;


	private final ProfessorRepository professorRepository;


	private final InscricaoRepository inscricaoRepository;


	private final TurmaRepository turmaRepository;

	@Autowired
	private  TurmaService turmaService;

	@Autowired
	private  InscricaoService inscricaoService;

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private ProfessorService professorService;
    public ProjdevwebApplication(AlunoRepository alunoRepository, ProfessorRepository professorRepository, InscricaoRepository inscricaoRepository, TurmaRepository turmaRepository) {

        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.inscricaoRepository = inscricaoRepository;
        this.turmaRepository = turmaRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(ProjdevwebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Aluno gabi = new Aluno ("Gabi", "Gabi@gmail");
//		Aluno maisa = new Aluno ("maisa", "maisa@gmail");
//		alunoRepository.save(gabi);
//		alunoRepository.save(maisa);
//
//		Turma t1 = new Turma(2025, 2);
//		Turma t2 = new Turma(2025,1);
//		turmaRepository.save(t1);
//		turmaRepository.save(t2);
//
//		Professor carlos = new Professor("carlos", "carlos@gmail");
//		professorRepository.save(carlos);
//
//		turmaService.associarProfessor(t1.getId(), carlos.getId());
//
//		inscricaoService.cadastrarInscricao(gabi.getId(), t1.getId());

		Scanner scanner = new Scanner(System.in);
		boolean continua = true;

		while (continua) {
			System.out.println("\n===== MENU PRINCIPAL =====");
			System.out.println("1. Gerenciar Alunos");
			System.out.println("2. Gerenciar Professores");
			System.out.println("3. Gerenciar Turmas");
			System.out.println("4. Gerenciar Inscrições");
			System.out.println("5. Sair");
			System.out.print("Escolha uma opção: ");

			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
				case 1:
					gerenciarAlunos(scanner);
					break;
				case 2:
					gerenciarProfessores(scanner);
					break;
				case 3:
					gerenciarTurmas(scanner);
					break;
				case 4:
					gerenciarInscricoes(scanner);
					break;
				case 5:
					continua = false;
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção inválida!");
			}
		}
		scanner.close();
	}


	private void gerenciarAlunos(Scanner scanner) {
		System.out.println("\n--- Gerenciar Alunos ---");
		System.out.println("1. Cadastrar Aluno");
		System.out.println("2. Alterar Aluno");
		System.out.println("3. Remover Aluno");
		System.out.println("4. Listar Todos os Alunos");
		System.out.println("5. Buscar Aluno por ID");
		System.out.print("Escolha uma opção: ");
		int opcao = scanner.nextInt();
		scanner.nextLine();

		try {
			switch (opcao) {
				case 1:
					System.out.print("Nome do Aluno: ");
					String nome = scanner.nextLine();
					System.out.print("Email do Aluno: ");
					String email = scanner.nextLine();
					Aluno novoAluno = new Aluno(nome, email);
					alunoService.cadastrarAluno(novoAluno);
					System.out.println("Aluno cadastrado com sucesso!");
					break;

				case 2: {
					System.out.print("ID do Aluno para alterar: ");
					Long idAlterar = scanner.nextLong();
					scanner.nextLine();

					Aluno alunoParaAlterar = alunoService.recuperarAlunoPorId(idAlterar);
					System.out.println("Dados atuais -> Nome: " + alunoParaAlterar.getNome() + ", Email: " + alunoParaAlterar.getEmail());


					System.out.println("\nO que você deseja alterar?");
					System.out.println("1. Nome");
					System.out.println("2. Email");
					System.out.print("Escolha uma opção: ");
					int opcaoAlteracao = scanner.nextInt();
					scanner.nextLine();

					switch (opcaoAlteracao) {
						case 1:
							System.out.print("Digite o novo nome: ");
							String novoNome = scanner.nextLine();
							alunoParaAlterar.setNome(novoNome);
							break;
						case 2:
							System.out.print("Digite o novo email: ");
							String novoEmail = scanner.nextLine();
							alunoParaAlterar.setEmail(novoEmail);
							break;
						default:
							System.out.println("Opção de alteração inválida!");
							return;
					}

					alunoService.alterarAluno(alunoParaAlterar);
					System.out.println("Aluno alterado com sucesso!");
					break;
				}

				case 3:
					System.out.print("ID do Aluno para remover: ");
					Long idRemover = scanner.nextLong();
					scanner.nextLine();
					alunoService.removerAlunoPorId(idRemover);
					System.out.println("Aluno removido com sucesso!");
					break;

				case 4:
					List<Aluno> alunos = alunoService.recuperarAlunos();
					System.out.println("\n--- Lista de Alunos ---");
					alunos.forEach(a -> System.out.println("ID: " + a.getId() + ", Nome: " + a.getNome() + ", Email: " + a.getEmail()));
					break;

				case 5:
					System.out.print("ID do Aluno para buscar: ");
					Long idBuscar = scanner.nextLong();
					scanner.nextLine();
					Aluno a = alunoService.recuperarAlunoPorId(idBuscar);
					System.out.println("Encontrado -> ID: " + a.getId() + ", Nome: " + a.getNome() + ", Email: " + a.getEmail());
					break;

				default:
					System.out.println("Opção inválida!");
			}
		} catch (Exception e) {
			System.err.println("Erro: " + e.getMessage());
		}
	}

	private void gerenciarProfessores(Scanner scanner) {
		System.out.println("\n--- Gerenciar Professores ---");
		System.out.println("1. Cadastrar Professor");
		System.out.println("2. Alterar Professor");
		System.out.println("3. Remover Professor");
		System.out.println("4. Listar Todos os Professores");
		System.out.println("5. Buscar Professor por ID");
		System.out.print("Escolha uma opção: ");
		int opcao = scanner.nextInt();
		scanner.nextLine();

		try {
			switch (opcao) {
				case 1:
					System.out.print("Nome do Professor: ");
					String nome = scanner.nextLine();
					System.out.print("Email do Professor: ");
					String email = scanner.nextLine();
					Professor novoProfessor = new Professor(nome, email);
					professorService.cadastrarProfessor(novoProfessor);
					System.out.println("Professor cadastrado com sucesso!");
					break;

				case 2: {
					System.out.print("ID do Professor para alterar: ");
					Long idAlterar = scanner.nextLong();
					scanner.nextLine();

					Professor professorParaAlterar = professorService.recuperarProfessorPorId(idAlterar);
					System.out.println("Dados atuais -> Nome: " + professorParaAlterar.getNome() + ", Email: " + professorParaAlterar.getEmail());

					System.out.println("\nO que você deseja alterar?");
					System.out.println("1. Nome");
					System.out.println("2. Email");
					System.out.print("Escolha uma opção: ");
					int opcaoAlteracao = scanner.nextInt();
					scanner.nextLine();

					switch (opcaoAlteracao) {
						case 1:
							System.out.print("Digite o novo nome: ");
							String novoNome = scanner.nextLine();
							professorParaAlterar.setNome(novoNome);
							break;
						case 2:
							System.out.print("Digite o novo email: ");
							String novoEmail = scanner.nextLine();
							professorParaAlterar.setEmail(novoEmail);
							break;
						default:
							System.out.println("Opção de alteração inválida!");
							return; // Sai do método se a opção for inválida
					}

					professorService.alterarProfessor(professorParaAlterar);
					System.out.println("Professor alterado com sucesso!");
					break;
				}

				case 3:
					System.out.print("ID do Professor para remover: ");
					Long idRemover = scanner.nextLong();
					scanner.nextLine();
					professorService.removerProfessorPorId(idRemover);
					System.out.println("Professor removido com sucesso!");
					break;

				case 4:
					List<Professor> professores = professorService.recuperarProfessores();
					System.out.println("\n--- Lista de Professores ---");
					professores.forEach(p -> System.out.println("ID: " + p.getId() + ", Nome: " + p.getNome() + ", Email: " + p.getEmail()));
					break;

				case 5:
					System.out.print("ID do Professor para buscar: ");
					Long idBuscar = scanner.nextLong();
					scanner.nextLine();
					Professor p = professorService.recuperarProfessorPorId(idBuscar);
					System.out.println("Encontrado -> ID: " + p.getId() + ", Nome: " + p.getNome() + ", Email: " + p.getEmail());
					break;

				default:
					System.out.println("Opção inválida!");
			}
		} catch (Exception e) {
			System.err.println("Erro: " + e.getMessage());
		}
	}

	private void gerenciarTurmas(Scanner scanner) {
		System.out.println("\n--- Gerenciar Turmas ---");
		System.out.println("1. Cadastrar Turma");
		System.out.println("2. Remover Turma");
		System.out.println("3. Associar Professor a Turma");
		System.out.print("Escolha uma opção: ");
		int opcao = scanner.nextInt();
		scanner.nextLine();

		try {
			switch (opcao) {
				case 1:
					System.out.print("Ano da Turma: ");
					int ano = scanner.nextInt();
					scanner.nextLine();
					System.out.print("Período da Turma: ");
					int periodo = scanner.nextInt();
					scanner.nextLine();
					Turma novaTurma = new Turma(ano, periodo);
					turmaService.cadastrarTurma(novaTurma);
					System.out.println("Turma cadastrada com sucesso!");
					break;
				case 2:
					System.out.print("ID da Turma para remover: ");
					Long idRemover = scanner.nextLong();
					scanner.nextLine();
					turmaService.removerTurmaPorId(idRemover);
					System.out.println("Turma removida com sucesso!");
					break;
				case 3: // Associar Professor
					System.out.print("ID da Turma: ");
					Long turmaId = scanner.nextLong();
					scanner.nextLine();
					System.out.print("ID do Professor para associar: ");
					Long profId = scanner.nextLong();
					scanner.nextLine();
					turmaService.associarProfessor(turmaId, profId);
					System.out.println("Professor associado com sucesso!");
					break;
				default:
					System.out.println("Opção inválida!");
			}
		} catch (Exception e) {
			System.err.println("Erro: " + e.getMessage());
		}
	}

	private void gerenciarInscricoes(Scanner scanner) {
		System.out.println("\n--- Gerenciar Inscrições ---");
		System.out.println("1. Realizar Inscrição (Cadastrar)");
		System.out.println("2. Cancelar Inscrição (Remover)");
		System.out.print("Escolha uma opção: ");
		int opcao = scanner.nextInt();
		scanner.nextLine();

		try {
			switch (opcao) {
				case 1:
					System.out.print("ID do Aluno a ser inscrito: ");
					Long alunoId = scanner.nextLong();
					scanner.nextLine();
					System.out.print("ID da Turma: ");
					Long turmaId = scanner.nextLong();
					scanner.nextLine();
					inscricaoService.cadastrarInscricao(alunoId, turmaId);
					System.out.println("Inscrição realizada com sucesso!");
					break;
				case 2:
					System.out.print("ID da Inscrição para remover: ");
					Long idRemover = scanner.nextLong();
					scanner.nextLine();
					inscricaoService.removerInscricaoPorId(idRemover);
					System.out.println("Inscrição removida com sucesso!");
					break;
				default:
					System.out.println("Opção inválida!");
			}
		} catch (Exception e) {
			System.err.println("Erro: " + e.getMessage());
		}
	}
}
