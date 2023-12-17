export class Language {
  constructor(
    public display: string,
    public code: string
  ) {
  }
}

/**
 * Used to dynamically build the UI, must be updated with each new "xx.json" file.
 */
export const langs = [
  new Language('English', 'en'),
  new Language('Deutsch', 'de')
]

export const defaultCode = 'en'
