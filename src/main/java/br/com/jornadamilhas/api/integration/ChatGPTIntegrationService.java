package br.com.jornadamilhas.api.integration;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Service;

@Service
public class ChatGPTIntegrationService {
    private static final String  API_KEY = System.getenv("api_key");
    private final OpenAiService service = new OpenAiService(API_KEY);
    public String geraTextoDestino(String destino) {

        String prompt =
                String.format("Aja como um redator para um site de venda de viagens. " +
                        "Faça um resumo sobre o local %s. Enfatize os pontos positivos da cidade." +
                        "Utilize uma linguagem informal. " +
                        "Cite ideias de passeios neste lugar. " +
                        "Crie 2 parágrafos neste resumo.", destino);

        CompletionRequest request = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(prompt)
                .maxTokens(2048)
                .temperature(0.6)
                .build();

        return service.createCompletion(request)
                .getChoices()
                .get(0)
                .getText();
    }
}
