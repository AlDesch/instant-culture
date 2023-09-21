# instant-culture

V1
- Le mode principale, One try: lancer l'application et les questions se lance automatiquement
- UI simple, background fix, bouton basique
- 4 types de liste de question (facile, intermédiaire, hard, impossible)
- Les questions sont piochées en mode aléatoires dans chaque liste selon ce modèle: 4 faciles, 4 intermédiaires, 4 difficiles, 1 impossible
- 4 réponses sont disponibles pour chaque question
- Les questions sont écrites au préalable dans un fichier JSON en local se ce format:
```
{
  "eazy" : {
    {
      "question" : "Quelle est la capitale de la France ?",
      "response": 1
      "proposal": {
        "p1": "Paris",
        "p2": "Lyon",
        "p3": "Lille",
        "p4": "Montpellier"
      },
    },
    {
      "question": "Qu'est-ce qu'une figure de style ?",
      "response": 1,
      "proposal": {
        "p1": "Une utilisation créative du langage pour embellir un texte",
        "p2": "Une célébrité dans le monde de la mode",
        "p3": "Un outil informatique pour créer des graphiques",
        "p4": "Une danse traditionnelle dans certaines cultures"
      }
    }
  },
  "hard" : {
    {
      "question" : "Quelle est la capitale de la France ?",
      "response": 1
      "proposal": {
        "p1": "Paris",
        "p2": "Lyon",
        "p3": "Lille",
        "p4": "Montpellier"
      },
    },
    {
      "question": "Qu'est-ce qu'une figure de style ?",
      "response": 1,
      "proposal": {
        "p1": "Une utilisation créative du langage pour embellir un texte",
        "p2": "Une célébrité dans le monde de la mode",
        "p3": "Un outil informatique pour créer des graphiques",
        "p4": "Une danse traditionnelle dans certaines cultures"
      }
    }
  },
}

```

V1.2
- Ajouter un écran de titre avec un bouton commencer, choix du thème, bouton dark mode
- Ajouter des thèmes aux questions pour choisir le thème jouable (jeux videos, science, subtilité de la langue Fr, Informatique etc... )
- Ajouter un Timer pour chaque question (different selon la difficulté de la question)
- Upgrade l'UI avec un background animé, bouton stylisé

V1.3
- Connecter a FireBase pour stocker les questions en base
- Pouvoir sauvgarder le meilleur score
- Ajouter des questions personnalisé via l'écran de titre