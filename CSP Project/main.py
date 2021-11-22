import math
from quiz import prompts;
from random import *;

iq_map = [ 
    { "range": [0, 200], "iq": 50 }, 
    { "range": [201, 400], "iq": 75 }, 
    { "range": [401, 600], "iq": 100 } 
];

def map_score_to_iq(score):
    for i in range(len(iq_map)):
        if (iq_map[i]["range"][0] <= score <= iq_map[i]["range"][1]):
            return iq_map[i]["iq"];

    return -1;

def eq_score_to_iq(score):
    return 0.1 * score * math.log(score, math.e);

def main():
    finished = False;
    questions_answered = 0;
    questions_correct = 0;
    total_questions = len(prompts) - 1;
    total_score = 0;

    while (not finished):
        if (questions_answered == total_questions):
            finished = True;

        prompt = prompts[randrange(0, len(prompts))];
        guess = str(input(str.format("{}\n", prompt["question"])))

        if (guess == prompt["answer"]):
            questions_correct += 1;
            total_score += prompt["score"];

        prompts.remove(prompt);

        questions_answered += 1;

    print(str.format("You answered {} question(s) correct with a score of {}", questions_correct, total_score))
    print(str.format("Your score of {} has translated to an iq of {} / {}", total_score, map_score_to_iq(total_score), eq_score_to_iq(total_score)));

if __name__ == "__main__":
    main();    