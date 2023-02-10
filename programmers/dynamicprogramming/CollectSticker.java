class CollectSticker {

    static int[] dp0;
    static int[] dp1;

    public int solution(int sticker[]) {

        int stickerCount = sticker.length;
        if (stickerCount == 1) return sticker[0];

        dp0 = new int[stickerCount];
        dp1 = new int[stickerCount];

        dp0[0] = sticker[0]; dp0[1] = sticker[0];
        for (int i = 2; i < stickerCount - 1; i++) {
            dp0[i] = Math.max(dp0[i - 2] + sticker[i], dp0[i - 1]);
        }
        dp0[stickerCount - 1] = dp0[stickerCount - 2];

        dp1[0] = 0; dp1[1] = sticker[1];
        for (int i = 2; i < stickerCount; i++) {
            dp1[i] = Math.max(dp1[i - 2] + sticker[i], dp1[i - 1]);
        }

        return Math.max(dp0[stickerCount - 1], dp1[stickerCount - 1]);
    }
}
