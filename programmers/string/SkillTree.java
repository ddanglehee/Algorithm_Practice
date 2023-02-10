class SkillTree {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        boolean correct;
        int skillIndex;
        for (String skill_tree: skill_trees) {
            skillIndex = 0;
            correct = true;
            for (int i = 0; i < skill_tree.length(); i++) {
                if (!skill.contains(Character.toString(skill_tree.charAt(i)))) continue;

                if (skill_tree.charAt(i) == skill.charAt(skillIndex)) {
                    skillIndex++;
                } else {
                    correct = false;
                    break;
                }
            }

            if (correct) answer++;
        }

        return answer;
    }
}
