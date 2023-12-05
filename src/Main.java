import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

class Task {
    private String description;
    private String category;
    private String status;

    public Task(String description, String category, String status) {
        this.description = description;
        this.category = category;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Descrição: " + description + ", Categoria: " + category + ", Status: " + status;
    }
}

public class Main {

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        while (option != 6) {
            System.out.println("----- TO-DO List -----");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Mostrar Tarefas");
            System.out.println("3. Exibir por Categoria");
            System.out.println("4. Marcar Tarefa como Concluída");
            System.out.println("5. Marcar Tarefa como A Fazer");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addTask(scanner, tasks);
                    break;
                case 2:
                    showTasks(tasks);
                    break;
                case 3:
                    showTasksByCategory(scanner, tasks);
                    break;
                case 4:
                    markAsCompleted(scanner, tasks);
                    break;
                case 5:
                    markAsToDo(scanner, tasks);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        }

        scanner.close();
    }

    private static void addTask(Scanner scanner, ArrayList<Task> tasks) {
        scanner.nextLine(); // Limpar o buffer
        System.out.print("Digite a descrição da tarefa: ");
        String description = scanner.nextLine();
        System.out.print("Digite a categoria da tarefa: ");
        String category = scanner.nextLine();
        System.out.print("Digite o status da tarefa (Concluída/A Fazer): ");
        String status = scanner.nextLine();

        Task newTask = new Task(description, category, status);
        tasks.add(newTask);
        System.out.println("Tarefa adicionada!");
    }

    private static void showTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Lista de tarefas vazia.");
        } else {
            System.out.println("Tarefas:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void showTasksByCategory(Scanner scanner, ArrayList<Task> tasks) {
        System.out.print("Digite a categoria para exibir as tarefas: ");
        String categoryToFilter = scanner.nextLine();

        ArrayList<Task> filteredTasks = tasks.stream()
                .filter(task -> task.getCategory().equalsIgnoreCase(categoryToFilter))
                .collect(Collectors.toCollection(ArrayList::new));

        if (filteredTasks.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada para essa categoria.");
        } else {
            System.out.println("Tarefas da categoria '" + categoryToFilter + "':");
            for (int i = 0; i < filteredTasks.size(); i++) {
                System.out.println((i + 1) + ". " + filteredTasks.get(i));
            }
        }
    }

    private static void markAsCompleted(Scanner scanner, ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Lista de tarefas vazia.");
        } else {
            System.out.print("Digite o número da tarefa concluída: ");
            int taskNumber = scanner.nextInt();
            scanner.nextLine();
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                Task task = tasks.get(taskNumber - 1);
                task.setStatus("Concluída");
                System.out.println("Tarefa marcada como concluída!");
            } else {
                System.out.println("Número de tarefa inválido.");
            }
        }
    }

    private static void markAsToDo(Scanner scanner, ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Lista de tarefas vazia.");
        } else {
            System.out.print("Digite o número da tarefa para marcar como 'A Fazer': ");
            int taskNumber = scanner.nextInt();
            scanner.nextLine();
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                Task task = tasks.get(taskNumber - 1);
                task.setStatus("A Fazer");
                System.out.println("Tarefa marcada como 'A Fazer'!");
            } else {
                System.out.println("Número de tarefa inválido.");
            }
        }
    }
}
