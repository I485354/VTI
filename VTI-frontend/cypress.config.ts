import { defineConfig } from "cypress";
/*"https://vti-frontend.vercel.app"*/
export default defineConfig({
  e2e: {
    baseUrl: "http://localhost:4200/",
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
  },

  component: {
    devServer: {
      framework: "angular",
      bundler: "webpack",
    },
    specPattern: "**/*.cy.ts",
  },
});
