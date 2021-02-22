public static int[] randomPermutation(int n) {
        
        // Construct a new array 'p' with length 'n'
        int[] p = new int[n];
        // Initialize 'p' with values from 1-n
        for (int i = 0; i < n; i++) p[i] = i + 1;
        
        // Construct a new array 'r' with length 'n'
        int[] r = new int[n];

        do {
            // Choose a random usable index from 0-n
            int i = (int)(Math.random() * n);
            
            // Copy the value at index 'i' into 'c'
            int c = p[i];

            // Set the last non-used element of array 'r' to 'c'
            r[n-1] = c;

            // Set the value at 'i' to the last usable element of 'p'
            p[i] = p[n-1];

            // Decrement 'n'
            n--;
        } while (n > 0);

        return r;
    }
