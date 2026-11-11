import { useEffect } from "react";

export default function VLibras() {
  useEffect(() => {
    // Evita duplicar o widget (React StrictMode roda o effect 2x em dev)
    if (document.querySelector("[vw]")) return;

    // 1. Estrutura HTML exigida pelo plugin
    const container = document.createElement("div");
    container.setAttribute("vw", "");
    container.className = "enabled";
    container.innerHTML = `
      <div vw-access-button class="active"></div>
      <div vw-plugin-wrapper>
        <div class="vw-plugin-top-wrapper"></div>
      </div>
    `;
    document.body.appendChild(container);

    // 2. Script oficial do VLibras
    const script = document.createElement("script");
    script.src = "https://vlibras.gov.br/app/vlibras-plugin.js";
    script.async = true;

    // 3. Inicializa o widget quando o script terminar de carregar
    script.onload = () => {
      if (window.VLibras) {
        new window.VLibras.Widget("https://vlibras.gov.br/app");
      }
    };
    document.body.appendChild(script);
  }, []);

  return null; // não renderiza nada no JSX
}