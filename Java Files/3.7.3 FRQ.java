public int findHorseSpace(String name)
{ /* to be implemented in part a */ 
    int index = -1;
    for (int i = 0; i < this.spaces.size(); i++) {
        if (this.spaces.get(i).equals(name)) {
            index = i;
            break;
        }
    }
    return index;
}

// ------------------------------------

public void consolidate()
{ /* to be implemented in part b */ 
   for (int i = 0; i < this.spaces.size(); i++) {
       // Find a null element
       if (this.spaces.get(i) == null) {
           // Once a null element is found, loop till we find a non-null element starting at i
           for (int j = i; j < this.spaces.size(); j++) {
               // Once we find the next non-null element, swap it with the null element at i
               if (this.spaces.get(j) != null) {
                    Horse temp = this.spaces.get(i);
                    this.spaces.set(i, this.spaces.get(j));
                    this.spaces.set(j, temp);
                    break;
               }
           }
       }
   }
}
